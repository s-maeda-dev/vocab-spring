package com.vocabulary.vocab_spring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class QuizSessionDto implements Serializable {
    private Long categoryId; // null means all categories
    private int totalQuestions; // 5 or 10
    private int currentQuestionNumber; // 1-based index
    private int correctAnswers;
    private Long currentWordId; // currently displayed Word ID

    /** 出題モード: ALL=全単語から出題, WEAK=苦手単語のみ出題 */
    private QuizMode quizMode = QuizMode.ALL;

    private List<Long> askedWordIds = new ArrayList<>();
    private boolean isInsufficientWords = false; // true if total available words < totalQuestions

    private List<QuizResultDto> results = new ArrayList<>();

    @Data
    public static class QuizResultDto implements Serializable {
        private String term;
        private String meaning;
        private String exampleSentence; // 例文（あれば表示）
        private String userAnswer;
        private boolean isCorrect;
    }
}
