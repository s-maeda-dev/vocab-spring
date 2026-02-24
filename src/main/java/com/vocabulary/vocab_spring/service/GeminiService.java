package com.vocabulary.vocab_spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    private static final Logger log = LoggerFactory.getLogger(GeminiService.class);

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getFeedback(int correctAnswers, int totalQuestions, List<String> recentMistakes) {
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("[GeminiService] GEMINI_API_KEY が未設定のため、AIコメントをスキップします。");
            return "（Gemini APIキーが設定されていないため、AIからのコメントはスキップされました。.envファイルのGEMINI_API_KEYを確認してください。）";
        }

        log.info("[GeminiService] APIキー設定済み。Gemini APIを呼び出します...");

        double correctRate = totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
        String mistakesText = recentMistakes.isEmpty() ? "特になし" : String.join(", ", recentMistakes);

        String prompt = String.format(
                "あなたはユーザーの単語・用語学習をサポートする優しいチューターです。以下の成績と今回のクイズで間違えた用語のリストを見て、ユーザーを励ます150文字程度の短いコメントを日本語で作成してください。\n" +
                        "・正答率: %d問中 %d問正解 (%.1f%%)\n" +
                        "・今回のクイズで間違えた用語: %s\n" +
                        "・今回のクイズ結果のみに基づいてコメントしてください（他の単語には触れないでください）\n" +
                        "・出力はコメントのテキストのみ（挨拶やマークダウン等は不要）",
                totalQuestions, correctAnswers, correctRate, mistakesText);

        return callGeminiApi(prompt);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private String callGeminiApi(String prompt) {
        try {
            String url = apiUrl + "?key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // リクエストボディの組み立て (Gemini 2.0 Flash 形式)
            Map<String, Object> content = new HashMap<>();
            Map<String, Object> part = new HashMap<>();
            part.put("text", prompt);
            content.put("parts", List.of(part));

            Map<String, Object> body = new HashMap<>();
            body.put("contents", List.of(content));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getBody() != null && response.getBody().containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> contentMap = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) contentMap.get("parts");
                    if (!parts.isEmpty()) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }
            return "AIからの応援メッセージを取得できませんでした。";
        } catch (Exception e) {
            log.error("[GeminiService] API呼び出し中にエラーが発生しました", e);
            return "（AIからの応援メッセージ取得中にエラーが発生しました）";
        }
    }
}
