package com.vocabulary.vocab_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 日別の学習統計データ。
 * 折れ線グラフで「正答数」「不正解数」の推移を表示するために使う。
 */
@Data
@AllArgsConstructor
public class DailyStatsDto {
    private String date; // 日付（"2026-02-22" 形式）
    private long correctCount; // その日の正答数
    private long incorrectCount; // その日の不正解数
}
