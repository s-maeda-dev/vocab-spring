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
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public QuizController(QuizService quizService, CategoryService categoryService,
            UserRepository userRepository, WordRepository wordRepository,
            QuizHistoryRepository quizHistoryRepository, GeminiService geminiService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.quizHistoryRepository = quizHistoryRepository;
        this.geminiService = geminiService;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("categories", categoryService.getCategoriesByUser(getCurrentUser()));
        return "quiz_settings";
    }

    @PostMapping("/start")
    public String startQuiz(@RequestParam(required = false) Long categoryId,
            @RequestParam int totalQuestions,
            @RequestParam(defaultValue = "false") boolean forceStart,
            Model model,
            HttpSession session) {
        User user = getCurrentUser();
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
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("totalQuestions", totalQuestions);
            model.addAttribute("wordCount", wordCount);
            return "quiz_confirm"; // 確認画面
        }

        QuizSessionDto quizSession = new QuizSessionDto();
        quizSession.setCategoryId(categoryId);
        quizSession.setTotalQuestions(totalQuestions);
        quizSession.setCurrentQuestionNumber(1);
        quizSession.setCorrectAnswers(0);
        quizSession.setInsufficientWords(wordCount < totalQuestions);
        session.setAttribute("quizSession", quizSession);
        return "redirect:/quiz";
    }

    @GetMapping
    public String showQuiz(HttpSession session, Model model) {
        QuizSessionDto quizSession = (QuizSessionDto) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz/settings";
        }

        if (quizSession.getCurrentQuestionNumber() > quizSession.getTotalQuestions()) {
            return "redirect:/quiz/summary"; // Phase5: まとめ画面へ
        }

        User user = getCurrentUser();

        java.util.List<Long> excludedIds;
        if (quizSession.isInsufficientWords()) {
            excludedIds = new java.util.ArrayList<>();
            if (!quizSession.getAskedWordIds().isEmpty()) {
                excludedIds.add(quizSession.getAskedWordIds().get(quizSession.getAskedWordIds().size() - 1));
            }
        } else {
            excludedIds = quizSession.getAskedWordIds();
        }

        Word randomWord;
        if (quizSession.getCategoryId() != null) {
            randomWord = quizService.getRandomWordByCategory(user, quizSession.getCategoryId(), excludedIds);
        } else {
            randomWord = quizService.getRandomWord(user, excludedIds);
        }

        if (randomWord == null) {
            // 該当除外で単語が見つからない場合(登録が1件のみ等)は除外なしで取得
            if (quizSession.getCategoryId() != null) {
                randomWord = quizService.getRandomWordByCategory(user, quizSession.getCategoryId(), null);
            } else {
                randomWord = quizService.getRandomWord(user, null);
            }
            if (randomWord == null) {
                model.addAttribute("error", "該当する単語が登録されていません。");
                model.addAttribute("categories", categoryService.getCategoriesByUser(getCurrentUser()));
                return "quiz_settings";
            }
        }

        quizSession.setCurrentWordId(randomWord.getId());
        model.addAttribute("word", randomWord);
        model.addAttribute("quizSession", quizSession);
        return "quiz";
    }

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

        // 履歴をDBに保存
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

        // 次の問題のために問題番号を進める
        quizSession.getAskedWordIds().add(currentWord.getId());
        quizSession.setCurrentQuestionNumber(quizSession.getCurrentQuestionNumber() + 1);

        return "quiz_result";
    }

    @GetMapping("/summary")
    public String showSummary(HttpSession session, Model model) {
        QuizSessionDto quizSession = (QuizSessionDto) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz/settings";
        }

        User user = getCurrentUser();
        java.util.List<String> recentMistakes = quizHistoryRepository.findRecentMistakesByUserId(user.getId());

        String aiFeedback = geminiService.getFeedback(
                quizSession.getCorrectAnswers(),
                quizSession.getResults().size(),
                recentMistakes);

        String[] quotes = {
                "「天才とは、1％のひらめきと99％の努力である。」 - トーマス・エジソン",
                "「失敗したわけではない。それを誤りだと言ってはいけない。勉強したのだと言いたまえ。」 - トーマス・エジソン",
                "「ステップ・バイ・ステップ。どんなことでも、何かを達成する場合にとるべき方法はただひとつ、一歩ずつ着実に立ち向かうことだ。」 - マイケル・ジョーダン",
                "「人生最大の栄光は、決して倒れないことではない。倒れるたびに起き上がることである。」 - ネルソン・マンデラ"
        };
        String randomQuote = quotes[new java.util.Random().nextInt(quotes.length)];

        model.addAttribute("quizSession", quizSession);
        model.addAttribute("aiFeedback", aiFeedback);
        model.addAttribute("quote", randomQuote);

        // セッションをクリアする
        session.removeAttribute("quizSession");
        return "quiz_summary";
    }
}
