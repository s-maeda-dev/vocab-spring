package com.vocabulary.vocab_spring.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class QuizSessionDto implements Serializable {
    private Long categoryId; // null means all categories
    private int totalQuestions; // 5 or 10
    private int currentQuestionNumber; // 1-based index
    private int correctAnswers;
    private Long currentWordId; // currently displayed Word ID

    /** 出題モード: "all"=全単語から出題, "weak"=苦手単語のみ出題 */
    private String quizMode = "all";

    private java.util.List<Long> askedWordIds = new java.util.ArrayList<>();
    private boolean isInsufficientWords = false; // true if total available words < totalQuestions

    private java.util.List<QuizResultDto> results = new java.util.ArrayList<>();

    @Data
    public static class QuizResultDto implements Serializable {
        private String term;
        private String meaning;
        private String exampleSentence; // 例文（あれば表示）
        private String userAnswer;
        private boolean isCorrect;
    }
}
