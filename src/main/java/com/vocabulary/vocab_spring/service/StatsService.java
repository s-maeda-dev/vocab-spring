package com.vocabulary.vocab_spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vocabulary.vocab_spring.dto.CategoryDetailStatsDto;
import com.vocabulary.vocab_spring.dto.DailyStatsDto;
import com.vocabulary.vocab_spring.dto.TotalStatsDto;
import com.vocabulary.vocab_spring.dto.WeakWordDto;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.repository.QuizHistoryRepository;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ユーザーの学習統計データを計算・取得するサービス。
 * 統計画面（stats.html）で使用する各種データを提供する。
 *
 * 【N+1問題対策について】
 * getCategoryDetailStats() は、カテゴリ数にかかわらず常に4回のDBクエリで完結する。
 * （カテゴリ基本統計1回 + 登録単語数1回 + 苦手単語数1回 + 日別統計1回）
 */
@Service
public class StatsService {

    private final QuizHistoryRepository quizHistoryRepository;
    private final WordRepository wordRepository;

    @Autowired
    public StatsService(QuizHistoryRepository quizHistoryRepository, WordRepository wordRepository) {
        this.quizHistoryRepository = quizHistoryRepository;
        this.wordRepository = wordRepository;
    }

    /**
     * ユーザーの累計統計（総解答数・正答率・登録単語数・苦手単語数）を返す。
     */
    public TotalStatsDto getTotalStats(User user) {
        // findTotalStatsByUserId は JPQL の複数集計のため List<Object[]> を返す
        List<Object[]> rows = quizHistoryRepository.findTotalStatsByUserId(user.getId());
        long totalAnswered = 0;
        long totalCorrect = 0;
        if (!rows.isEmpty()) {
            Object[] stats = rows.get(0);
            totalAnswered = stats[0] != null ? ((Number) stats[0]).longValue() : 0;
            totalCorrect = stats[1] != null ? ((Number) stats[1]).longValue() : 0;
        }
        double correctRate = totalAnswered > 0 ? (double) totalCorrect / totalAnswered * 100 : 0;

        long totalWords = wordRepository.countByUserId(user.getId());
        long weakWordCount = quizHistoryRepository.countWeakWordsByUserId(user.getId());

        return new TotalStatsDto(totalAnswered, totalCorrect, correctRate, totalWords, weakWordCount);
    }

    /**
     * カテゴリごとに詳細統計（解答数・正答率・登録単語数・苦手単語数・日別推移）をまとめて返す。
     * 統計画面のカテゴリ別セクション表示に使用する。
     *
     * N+1問題対策: カテゴリ数にかかわらず計4回のDBクエリで完結する。
     * 1. カテゴリ別基本統計（解答数・正答数）
     * 2. 全カテゴリの登録単語数（一括）
     * 3. 全カテゴリの苦手単語数（一括）
     * 4. 全カテゴリの日別統計（一括）
     *
     * @param user ログインユーザー
     * @param days 日別推移グラフで表示する日数（例: 14）
     * @return カテゴリ別詳細統計のリスト
     */
    public List<CategoryDetailStatsDto> getCategoryDetailStats(User user, int days) {
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(days);
        Long userId = user.getId();

        // ── クエリ1: カテゴリ別基本統計（解答数・正答数） ──
        List<Object[]> categoryRows = quizHistoryRepository.findCategoryStatsByUserId(userId);

        // ── クエリ2: 全カテゴリの登録単語数を一括取得 ──
        // キー=カテゴリ名, 値=登録単語数 のマップに変換する
        Map<String, Long> wordCountMap = buildLongMap(
                wordRepository.findWordCountPerCategoryByUserId(userId));

        // ── クエリ3: 全カテゴリの苦手単語数を一括取得 ──
        // キー=カテゴリ名, 値=苦手単語数 のマップに変換する
        Map<String, Long> weakCountMap = buildLongMap(
                quizHistoryRepository.findWeakWordCountPerCategoryByUserId(userId));

        // ── クエリ3.5: 全カテゴリの苦手単語リストを一括取得 ──
        Map<String, List<WeakWordDto>> weakWordsMap = buildWeakWordDtoMap(
                quizHistoryRepository.findWeakWordsListPerCategoryByUserId(userId));

        // ── クエリ4: 全カテゴリの日別統計を一括取得 ──
        // キー=カテゴリ名, 値=DailyStatsDtoのリスト のマップに変換する
        Map<String, List<DailyStatsDto>> dailyStatsMap = buildDailyStatsMap(
                quizHistoryRepository.findDailyStatsPerCategoryByUserId(userId, sinceDate));

        // ── マップを参照して DTO を組み立てる（DBクエリなし） ──
        List<CategoryDetailStatsDto> result = new ArrayList<>();
        for (Object[] row : categoryRows) {
            String categoryName = (String) row[0];
            long totalAnswered = ((Number) row[1]).longValue();
            long totalCorrect = ((Number) row[2]).longValue();
            double correctRate = totalAnswered > 0 ? (double) totalCorrect / totalAnswered * 100 : 0;

            long wordCount = wordCountMap.getOrDefault(categoryName, 0L);
            long weakWordCount = weakCountMap.getOrDefault(categoryName, 0L);
            List<DailyStatsDto> dailyStats = dailyStatsMap.getOrDefault(categoryName, new ArrayList<>());
            List<WeakWordDto> weakWords = weakWordsMap.getOrDefault(categoryName, new ArrayList<>());

            result.add(new CategoryDetailStatsDto(
                    categoryName, totalAnswered, totalCorrect, correctRate,
                    wordCount, weakWordCount, dailyStats, weakWords));
        }
        return result;
    }

    // ─────────────────────────────────────────────
    // プライベートヘルパーメソッド
    // ─────────────────────────────────────────────

    /**
     * Object[][]: [String, Number] のリストを Map<String, Long> に変換するヘルパー。
     * カテゴリ名をキー、集計値を値として格納する。
     */
    private Map<String, Long> buildLongMap(List<Object[]> rows) {
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : rows) {
            String name = (String) row[0];
            long count = ((Number) row[1]).longValue();
            map.put(name, count);
        }
        return map;
    }

    /**
     * Object[][]: [カテゴリ名, 日付, 正答数, 不正解数] のリストを
     * Map<カテゴリ名, List<DailyStatsDto>> に変換するヘルパー。
     */
    private Map<String, List<DailyStatsDto>> buildDailyStatsMap(List<Object[]> rows) {
        Map<String, List<DailyStatsDto>> map = new HashMap<>();
        for (Object[] row : rows) {
            String categoryName = (String) row[0];
            String date = row[1].toString();
            long correct = ((Number) row[2]).longValue();
            long incorrect = ((Number) row[3]).longValue();
            // computeIfAbsent: キーがなければ空リストを作成してから追加する
            map.computeIfAbsent(categoryName, k -> new ArrayList<>())
                    .add(new DailyStatsDto(date, correct, incorrect));
        }
        return map;
    }

    private Map<String, List<WeakWordDto>> buildWeakWordDtoMap(List<Object[]> rows) {
        Map<String, List<WeakWordDto>> map = new HashMap<>();
        for (Object[] row : rows) {
            String categoryName = (String) row[0];
            String term = (String) row[1];
            String definition = (String) row[2];
            String exampleSentence = (String) row[3];
            map.computeIfAbsent(categoryName, k -> new ArrayList<>())
                    .add(new WeakWordDto(term, definition, exampleSentence));
        }
        return map;
    }
}
