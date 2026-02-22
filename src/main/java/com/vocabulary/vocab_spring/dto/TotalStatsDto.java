package com.vocabulary.vocab_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザーの累計学習統計データ。
 * 統計画面の上部に表示する数値カード用。
 */
@Data
@AllArgsConstructor
public class TotalStatsDto {
    private long totalAnswered; // 累計解答数
    private long totalCorrect; // 累計正答数
    private double correctRate; // 正答率（%）
    private long totalWords; // 登録単語数
    private long weakWordCount; // 苦手単語数
}
