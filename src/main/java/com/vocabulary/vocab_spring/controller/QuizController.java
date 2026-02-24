package com.vocabulary.vocab_spring.controller;

import java.util.ArrayList;
import java.util.List;

import com.vocabulary.vocab_spring.dto.QuizMode;
import com.vocabulary.vocab_spring.dto.QuizSessionDto;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.WordRepository;
import com.vocabulary.vocab_spring.service.CategoryService;
import com.vocabulary.vocab_spring.service.GeminiService;
import com.vocabulary.vocab_spring.service.QuizService;
import com.vocabulary.vocab_spring.service.QuoteService;
import com.vocabulary.vocab_spring.util.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final CategoryService categoryService;
    private final WordRepository wordRepository;
    private final GeminiService geminiService;
    private final QuoteService quoteService;
    private final SecurityUtils securityUtils;

    @Autowired
    public QuizController(QuizService quizService, CategoryService categoryService,
            WordRepository wordRepository,
            GeminiService geminiService,
            QuoteService quoteService,
            SecurityUtils securityUtils) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.wordRepository = wordRepository;
        this.geminiService = geminiService;
        this.quoteService = quoteService;
        this.securityUtils = securityUtils;
    }

    /**
     * 出題済みIDから除外リストを組み立てる共通メソッド。
     * 単語数が不足している場合は、連続出題を防ぐため直前の1問のみを除外する。
     */
    private List<Long> buildExcludedIds(QuizSessionDto quizSession) {
        if (quizSession.isInsufficientWords()) {
            List<Long> excludedIds = new ArrayList<>();
            if (!quizSession.getAskedWordIds().isEmpty()) {
                excludedIds.add(quizSession.getAskedWordIds().get(
                        quizSession.getAskedWordIds().size() - 1));
            }
            return excludedIds;
        }
        return quizSession.getAskedWordIds();
    }

    // ──────────────────────────────────────────────────────────────
    // クイズ設定画面
    // ──────────────────────────────────────────────────────────────

    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("categories", categoryService.getCategoriesByUser(securityUtils.getCurrentUser()));
        return "quiz_settings";
    }

    // ──────────────────────────────────────────────────────────────
    // クイズ開始: 設定を受け取りセッションを初期化する
    // ──────────────────────────────────────────────────────────────

    @PostMapping("/start")
    public String startQuiz(@RequestParam(required = false) Long categoryId,
            @RequestParam int totalQuestions,
            @RequestParam(defaultValue = "all") String quizMode,
            @RequestParam(defaultValue = "false") boolean forceStart,
            Model model,
            HttpSession session) {

        User user = securityUtils.getCurrentUser();
        QuizMode mode = QuizMode.fromString(quizMode);

        // ── 苦手単語モード ──
        if (mode == QuizMode.WEAK) {
            long weakWordCount = (categoryId != null)
                    ? quizService.countWeakWordsByCategory(user, categoryId)
                    : quizService.countWeakWords(user);

            if (weakWordCount == 0) {
                String errorMsg = (categoryId != null)
                        ? "指定カテゴリに苦手な単語がまだありません。まずは通常モードでクイズに挑戦してみましょう！"
                        : "苦手な単語がまだありません。まずは通常モードでクイズに挑戦してみましょう！";
                model.addAttribute("error", errorMsg);
                model.addAttribute("categories", categoryService.getCategoriesByUser(user));
                return "quiz_settings";
            }

            if (weakWordCount < totalQuestions && !forceStart) {
                model.addAttribute("quizMode", quizMode);
                model.addAttribute("categoryId", categoryId);
                model.addAttribute("totalQuestions", totalQuestions);
                model.addAttribute("wordCount", weakWordCount);
                return "quiz_confirm";
            }

            QuizSessionDto quizSession = new QuizSessionDto();
            quizSession.setQuizMode(QuizMode.WEAK);
            quizSession.setCategoryId(categoryId);
            quizSession.setTotalQuestions(totalQuestions);
            quizSession.setCurrentQuestionNumber(1);
            quizSession.setCorrectAnswers(0);
            quizSession.setInsufficientWords(weakWordCount < totalQuestions);
            session.setAttribute("quizSession", quizSession);
            return "redirect:/quiz";
        }

        // ── 全単語モード（デフォルト）──
        long wordCount;
        if (categoryId != null) {
            wordCount = quizService.getWordCountByCategory(user, categoryId);
        } else {
            wordCount = quizService.getWordCount(user);
        }

        if (wordCount == 0) {
            model.addAttribute("error", "該当する単語が登録されていません。");
            model.addAttribute("categories", categoryService.getCategoriesByUser(user));
            return "quiz_settings";
        }

        if (wordCount < totalQuestions && !forceStart) {
            model.addAttribute("quizMode", quizMode);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("totalQuestions", totalQuestions);
            model.addAttribute("wordCount", wordCount);
            return "quiz_confirm";
        }

        QuizSessionDto quizSession = new QuizSessionDto();
        quizSession.setQuizMode(QuizMode.ALL);
        quizSession.setCategoryId(categoryId);
        quizSession.setTotalQuestions(totalQuestions);
        quizSession.setCurrentQuestionNumber(1);
        quizSession.setCorrectAnswers(0);
        quizSession.setInsufficientWords(wordCount < totalQuestions);
        session.setAttribute("quizSession", quizSession);
        return "redirect:/quiz";
    }

    // ──────────────────────────────────────────────────────────────
    // クイズ画面: 問題を1問表示する
    // ──────────────────────────────────────────────────────────────

    @GetMapping
    public String showQuiz(HttpSession session, Model model) {
        QuizSessionDto quizSession = (QuizSessionDto) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz/settings";
        }

        if (quizSession.getCurrentQuestionNumber() > quizSession.getTotalQuestions()) {
            return "redirect:/quiz/summary";
        }

        User user = securityUtils.getCurrentUser();
        Word randomWord;
        List<Long> excludedIds = buildExcludedIds(quizSession);

        // ── 苦手単語モードの出題 ──
        if (quizSession.getQuizMode() == QuizMode.WEAK) {
            randomWord = quizService.getRandomWeakWord(user, quizSession.getCategoryId(), excludedIds);
            if (randomWord == null) {
                randomWord = quizService.getRandomWeakWord(user, quizSession.getCategoryId(), null);
            }

            // ── 全単語モードの出題 ──
        } else {
            if (quizSession.getCategoryId() != null) {
                randomWord = quizService.getRandomWordByCategory(user, quizSession.getCategoryId(), excludedIds);
            } else {
                randomWord = quizService.getRandomWord(user, excludedIds);
            }

            // 除外ありで見つからない場合は除外なしで再取得
            if (randomWord == null) {
                if (quizSession.getCategoryId() != null) {
                    randomWord = quizService.getRandomWordByCategory(user, quizSession.getCategoryId(), null);
                } else {
                    randomWord = quizService.getRandomWord(user, null);
                }
            }
        }

        if (randomWord == null) {
            model.addAttribute("error", "該当する単語が見つかりませんでした。");
            model.addAttribute("categories", categoryService.getCategoriesByUser(securityUtils.getCurrentUser()));
            return "quiz_settings";
        }

        quizSession.setCurrentWordId(randomWord.getId());
        model.addAttribute("word", randomWord);
        model.addAttribute("quizSession", quizSession);
        return "quiz";
    }

    // ──────────────────────────────────────────────────────────────
    // 解答処理: 正誤判定 & 履歴保存
    // ──────────────────────────────────────────────────────────────

    @PostMapping("/answer")
    public String checkAnswer(@RequestParam String answer, HttpSession session, Model model) {
        QuizSessionDto quizSession = (QuizSessionDto) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz/settings";
        }

        Word currentWord = wordRepository.findById(quizSession.getCurrentWordId()).orElse(null);
        if (currentWord == null) {
            return "redirect:/quiz/settings";
        }

        boolean isCorrect = false;
        if (currentWord.getTerm() != null) {
            String[] acceptedAnswers = currentWord.getTerm().split("[,，、]+");
            for (String accepted : acceptedAnswers) {
                if (accepted.trim().equalsIgnoreCase(answer.trim())) {
                    isCorrect = true;
                    break;
                }
            }
        }
        if (isCorrect) {
            quizSession.setCorrectAnswers(quizSession.getCorrectAnswers() + 1);
        }

        // 学習履歴をDBに保存
        quizService.saveQuizHistory(securityUtils.getCurrentUser(), currentWord, isCorrect);

        // セッションのリストにも追加（結果画面での表示用）
        QuizSessionDto.QuizResultDto resultDto = new QuizSessionDto.QuizResultDto();
        resultDto.setTerm(currentWord.getTerm());
        resultDto.setMeaning(currentWord.getDefinition());
        resultDto.setExampleSentence(currentWord.getExampleSentence());
        resultDto.setUserAnswer(answer);
        resultDto.setCorrect(isCorrect);
        quizSession.getResults().add(resultDto);

        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", answer);
        model.addAttribute("correctWord", currentWord);
        model.addAttribute("quizSession", quizSession);

        // 次の問題へ
        quizSession.getAskedWordIds().add(currentWord.getId());
        quizSession.setCurrentQuestionNumber(quizSession.getCurrentQuestionNumber() + 1);

        return "quiz_result";
    }

    // ──────────────────────────────────────────────────────────────
    // クイズまとめ画面: AIフィードバック & 名言を表示
    // ──────────────────────────────────────────────────────────────

    @GetMapping("/summary")
    public String showSummary(HttpSession session, Model model) {
        QuizSessionDto quizSession = (QuizSessionDto) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz/settings";
        }

        // 今回のクイズセッションで間違えた単語のみを抽出（単語と意味を含める）
        List<String> currentMistakes = quizSession.getResults().stream()
                .filter(r -> !r.isCorrect())
                .map(r -> "・" + r.getTerm() + ": " + r.getMeaning())
                .toList();

        // カテゴリ名を取得
        String categoryName = null;
        if (quizSession.getCategoryId() != null) {
            categoryName = categoryService.getCategoryById(quizSession.getCategoryId())
                    .map(com.vocabulary.vocab_spring.entity.Category::getName)
                    .orElse(null);
        }

        String aiFeedback = geminiService.getFeedback(
                quizSession.getCorrectAnswers(),
                quizSession.getResults().size(),
                categoryName,
                currentMistakes);

        // QuoteService から名言を取得（ランダム）
        String randomQuote = quoteService.getRandomQuote();

        model.addAttribute("quizSession", quizSession);
        model.addAttribute("aiFeedback", aiFeedback);
        model.addAttribute("quote", randomQuote);

        // セッションをクリア
        session.removeAttribute("quizSession");
        return "quiz_summary";
    }
}
