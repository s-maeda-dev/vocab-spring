package com.vocabulary.vocab_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * カテゴリごとに集約した学習統計データ。
 * 統計画面でカテゴリ別セクションを表示するために使う。
 */
@Data
@AllArgsConstructor
public class CategoryDetailStatsDto {
    private String categoryName; // カテゴリ名（未分類の場合は "未分類"）
    private long totalAnswered; // このカテゴリでの累計解答数
    private long totalCorrect; // このカテゴリでの累計正答数
    private double correctRate; // 正答率（%）
    private long wordCount; // このカテゴリに登録されている単語数
    private long weakWordCount; // このカテゴリの苦手単語数
    private List<DailyStatsDto> dailyStats; // 直近N日間の日別統計（折れ線グラフ用）
    private List<String> weakWords; // このカテゴリの苦手単語一覧
}
