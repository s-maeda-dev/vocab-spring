package com.vocabulary.vocab_spring.controller;

import com.vocabulary.vocab_spring.dto.QuizSessionDto;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.UserRepository;
import com.vocabulary.vocab_spring.repository.WordRepository;
import com.vocabulary.vocab_spring.repository.QuizHistoryRepository;
import com.vocabulary.vocab_spring.service.CategoryService;
import com.vocabulary.vocab_spring.service.GeminiService;
import com.vocabulary.vocab_spring.service.QuizService;
import com.vocabulary.vocab_spring.service.QuoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final QuizHistoryRepository quizHistoryRepository;
    private final GeminiService geminiService;
    private final QuoteService quoteService;

    @Autowired
    public QuizController(QuizService quizService, CategoryService categoryService,
            UserRepository userRepository, WordRepository wordRepository,
            QuizHistoryRepository quizHistoryRepository, GeminiService geminiService,
            QuoteService quoteService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.quizHistoryRepository = quizHistoryRepository;
        this.geminiService = geminiService;
        this.quoteService = quoteService;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ──────────────────────────────────────────────────────────────
    // クイズ設定画面
    // ──────────────────────────────────────────────────────────────

    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("categories", categoryService.getCategoriesByUser(getCurrentUser()));
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

        User user = getCurrentUser();

        // ── 苦手単語モード ──
        if ("weak".equals(quizMode)) {
            long weakWordCount = quizService.countWeakWords(user);

            if (weakWordCount == 0) {
                model.addAttribute("error", "苦手な単語がまだありません。まずは通常モードでクイズに挑戦してみましょう！");
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
            quizSession.setQuizMode("weak");
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
        quizSession.setQuizMode("all");
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

        User user = getCurrentUser();
        Word randomWord;

        // ── 苦手単語モードの出題 ──
        if ("weak".equals(quizSession.getQuizMode())) {
            java.util.List<Long> excludedIds;
            if (quizSession.isInsufficientWords()) {
                // 単語数が少ない場合は直前に出した単語だけ除外（連続出題を防ぐ）
                excludedIds = new java.util.ArrayList<>();
                if (!quizSession.getAskedWordIds().isEmpty()) {
                    excludedIds.add(quizSession.getAskedWordIds().get(quizSession.getAskedWordIds().size() - 1));
                }
            } else {
                excludedIds = quizSession.getAskedWordIds();
            }
            randomWord = quizService.getRandomWeakWord(user, excludedIds);
            if (randomWord == null) {
                randomWord = quizService.getRandomWeakWord(user, null);
            }

            // ── 全単語モードの出題 ──
        } else {
            java.util.List<Long> excludedIds;
            if (quizSession.isInsufficientWords()) {
                excludedIds = new java.util.ArrayList<>();
                if (!quizSession.getAskedWordIds().isEmpty()) {
                    excludedIds.add(quizSession.getAskedWordIds().get(quizSession.getAskedWordIds().size() - 1));
                }
            } else {
                excludedIds = quizSession.getAskedWordIds();
            }

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
            model.addAttribute("categories", categoryService.getCategoriesByUser(getCurrentUser()));
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
        quizService.saveQuizHistory(getCurrentUser(), currentWord, isCorrect);

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

        User user = getCurrentUser();

        // Pageableを使って最近の苦手単語を最大10件取得
        java.util.List<String> recentMistakes = quizHistoryRepository
                .findRecentMistakesByUserId(user.getId(), PageRequest.of(0, 10));

        String aiFeedback = geminiService.getFeedback(
                quizSession.getCorrectAnswers(),
                quizSession.getResults().size(),
                recentMistakes);

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
