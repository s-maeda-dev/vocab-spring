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

    public String getFeedback(int correctAnswers, int totalQuestions, String categoryName,
            List<String> mistakeDetails) {
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("[GeminiService] GEMINI_API_KEY が未設定のため、AIコメントをスキップします。");
            return "（Gemini APIキーが設定されていないため、AIからのコメントはスキップされました。.envファイルのGEMINI_API_KEYを確認してください。）";
        }

        log.info("[GeminiService] APIキー設定済み。Gemini APIを呼び出します...");

        double correctRate = totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
        String categoryText = (categoryName != null && !categoryName.isEmpty()) ? categoryName : "全般的な学習";
        String mistakesText = mistakeDetails.isEmpty() ? "なし（全問正解です！）" : String.join("\n", mistakeDetails);

        String prompt = String.format(
                "あなたはユーザーの「%s」の学習を支援する専門家です。以下の学習結果に基づき、学習者を励まし、間違えた問題については具体的な補足説明や覚え方のアドバイスを含む200文字程度のコメントを日本語で作成してください。\n\n"
                        +
                        "【学習結果】\n" +
                        "・カテゴリ: %s\n" +
                        "・正答率: %d問中 %d問正解 (%.1f%%)\n\n" +
                        "【間違えた用語と意味】\n" +
                        "%s\n\n" +
                        "【制約事項】\n" +
                        "・親しみやすく、かつ専門家らしい信頼感のある言葉遣い（です・ます調）で。\n" +
                        "・出力はコメントのテキストのみ（挨拶、タイトル、マークダウン記法、余計な前置きは不要）。\n" +
                        "・冒頭に空行を含めないでください。",
                categoryText, categoryText, totalQuestions, correctAnswers, correctRate, mistakesText);

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
