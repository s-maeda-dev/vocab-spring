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

    private java.util.List<Long> askedWordIds = new java.util.ArrayList<>();
    private boolean isInsufficientWords = false; // true if total available words < totalQuestions
}
