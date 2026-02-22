package com.vocabulary.vocab_spring.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vocabulary.vocab_spring.entity.QuizHistory;
import com.vocabulary.vocab_spring.entity.Word;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Long> {

        // ─────────────────────────────────────────────
        // 最近の間違い
        // ─────────────────────────────────────────────

        /**
         * ユーザーが最近間違えた単語の「単語名（term）」リストを取得する。
         * Pageable でページング（件数制限）を行うことで、LIMIT 句の方言依存を回避している。
         */
        @Query("SELECT qh.word.term FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false GROUP BY qh.word.id ORDER BY MAX(qh.answeredAt) DESC")
        List<String> findRecentMistakesByUserId(@Param("userId") Long userId, Pageable pageable);

        // ─────────────────────────────────────────────
        // 苦手単語: 全カテゴリ
        // ─────────────────────────────────────────────

        /**
         * ユーザーが苦手な単語（Wordエンティティ）を不正解回数の多い順・最近間違えた順で取得する。
         * カテゴリ絞り込みなし。
         */
        @Query("SELECT qh.word FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false GROUP BY qh.word.id ORDER BY COUNT(qh.id) DESC, MAX(qh.answeredAt) DESC")
        List<Word> findWeakWordsByUserId(@Param("userId") Long userId, Pageable pageable);

        /**
         * ユーザーが不正解になったことのある単語（苦手単語）のユニーク件数。
         * カテゴリ絞り込みなし。
         */
        @Query("SELECT COUNT(DISTINCT qh.word.id) FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false")
        long countWeakWordsByUserId(@Param("userId") Long userId);

        // ─────────────────────────────────────────────
        // 苦手単語: カテゴリ絞り込みあり（カテゴリID指定）
        // ─────────────────────────────────────────────

        /**
         * ユーザーが苦手な単語（Wordエンティティ）を、指定カテゴリに絞って取得する。
         * カテゴリIDで絞り込み + 不正解回数の多い順・最近間違えた順。
         */
        @Query("SELECT qh.word FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false AND qh.word.category.id = :categoryId GROUP BY qh.word.id ORDER BY COUNT(qh.id) DESC, MAX(qh.answeredAt) DESC")
        List<Word> findWeakWordsByUserIdAndCategoryId(@Param("userId") Long userId,
                        @Param("categoryId") Long categoryId,
                        Pageable pageable);

        /**
         * ユーザーが不正解になったことのある単語（苦手単語）のユニーク件数。
         * カテゴリIDで絞り込みあり。
         */
        @Query("SELECT COUNT(DISTINCT qh.word.id) FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false AND qh.word.category.id = :categoryId")
        long countWeakWordsByUserIdAndCategoryId(@Param("userId") Long userId,
                        @Param("categoryId") Long categoryId);

        // ─────────────────────────────────────────────
        // 統計: 日別正答数・不正解数（全カテゴリ）
        // ─────────────────────────────────────────────

        /**
         * 指定日以降の日別正答数・不正解数を返す（全カテゴリ合計）。
         * 戻り値は Object[][]: [日付(String), 正答数(Long), 不正解数(Long)]
         */
        @Query(value = "SELECT DATE(answered_at) AS answer_date, " +
                        "SUM(CASE WHEN is_correct = true THEN 1 ELSE 0 END) AS correct_count, " +
                        "SUM(CASE WHEN is_correct = false THEN 1 ELSE 0 END) AS incorrect_count " +
                        "FROM quiz_histories WHERE user_id = :userId AND answered_at >= :sinceDate " +
                        "GROUP BY DATE(answered_at) ORDER BY answer_date", nativeQuery = true)
        List<Object[]> findDailyStatsByUserId(@Param("userId") Long userId,
                        @Param("sinceDate") java.time.LocalDateTime sinceDate);

        // ─────────────────────────────────────────────
        // 統計: カテゴリ別正答数・解答数
        // ─────────────────────────────────────────────

        /**
         * カテゴリ別の解答数と正答数を返す。
         * 戻り値は Object[][]: [カテゴリ名(String), 解答数(Long), 正答数(Long)]
         */
        @Query(value = "SELECT COALESCE(c.name, '未分類') AS category_name, " +
                        "COUNT(qh.id) AS total_count, " +
                        "SUM(CASE WHEN qh.is_correct = true THEN 1 ELSE 0 END) AS correct_count " +
                        "FROM quiz_histories qh " +
                        "JOIN words w ON qh.word_id = w.id " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE qh.user_id = :userId " +
                        "GROUP BY c.name ORDER BY total_count DESC", nativeQuery = true)
        List<Object[]> findCategoryStatsByUserId(@Param("userId") Long userId);

        // ─────────────────────────────────────────────
        // 統計: 累計
        // ─────────────────────────────────────────────

        /**
         * ユーザーの累計解答数と正答数を返す。
         * 戻り値は List<Object[]>: 先頭要素が [累計解答数(Long), 累計正答数(Long)] の配列。
         * ※ JPQL で複数集計値を返す場合、Spring Data JPA は List<Object[]> で返すため List を使う。
         */
        @Query("SELECT COUNT(qh.id), SUM(CASE WHEN qh.isCorrect = true THEN 1 ELSE 0 END) FROM QuizHistory qh WHERE qh.user.id = :userId")
        List<Object[]> findTotalStatsByUserId(@Param("userId") Long userId);

        // ─────────────────────────────────────────────
        // 統計: カテゴリ名で絞り込んだ日別統計（単一カテゴリ）
        // ─────────────────────────────────────────────

        /**
         * 指定カテゴリ名・指定日以降の日別正答数・不正解数を返す（単一カテゴリ）。
         * COALESCE で category_id が null の単語を "未分類" として扱う。
         */
        @Query(value = "SELECT DATE(qh.answered_at) AS answer_date, " +
                        "SUM(CASE WHEN qh.is_correct = true THEN 1 ELSE 0 END) AS correct_count, " +
                        "SUM(CASE WHEN qh.is_correct = false THEN 1 ELSE 0 END) AS incorrect_count " +
                        "FROM quiz_histories qh " +
                        "JOIN words w ON qh.word_id = w.id " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE qh.user_id = :userId " +
                        "AND COALESCE(c.name, '未分類') = :categoryName " +
                        "AND qh.answered_at >= :sinceDate " +
                        "GROUP BY DATE(qh.answered_at) ORDER BY answer_date", nativeQuery = true)
        List<Object[]> findDailyStatsByUserIdAndCategoryName(@Param("userId") Long userId,
                        @Param("categoryName") String categoryName,
                        @Param("sinceDate") java.time.LocalDateTime sinceDate);

        // ─────────────────────────────────────────────
        // 統計: カテゴリ名で絞り込んだ苦手単語数（単一カテゴリ）
        // ─────────────────────────────────────────────

        /**
         * 指定カテゴリ名の苦手単語のユニーク件数を返す（単一カテゴリ）。
         */
        @Query(value = "SELECT COUNT(DISTINCT qh.word_id) " +
                        "FROM quiz_histories qh " +
                        "JOIN words w ON qh.word_id = w.id " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE qh.user_id = :userId AND qh.is_correct = false " +
                        "AND COALESCE(c.name, '未分類') = :categoryName", nativeQuery = true)
        long countWeakWordsByUserIdAndCategoryName(@Param("userId") Long userId,
                        @Param("categoryName") String categoryName);

        // ─────────────────────────────────────────────
        // 統計: 全カテゴリ一括取得（N+1問題対策）
        // ─────────────────────────────────────────────

        /**
         * ユーザーの全カテゴリにおける苦手単語数を一括取得する（N+1問題対策）。
         * 戻り値は Object[][]: [カテゴリ名(String), 苦手単語数(Long)]
         */
        @Query(value = "SELECT COALESCE(c.name, '未分類') AS category_name, " +
                        "COUNT(DISTINCT qh.word_id) AS weak_count " +
                        "FROM quiz_histories qh " +
                        "JOIN words w ON qh.word_id = w.id " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE qh.user_id = :userId AND qh.is_correct = false " +
                        "GROUP BY c.name", nativeQuery = true)
        List<Object[]> findWeakWordCountPerCategoryByUserId(@Param("userId") Long userId);

        /**
         * ユーザーの全カテゴリにおける日別正答数・不正解数を一括取得する（N+1問題対策）。
         * 戻り値は Object[][]: [カテゴリ名(String), 日付(String), 正答数(Long), 不正解数(Long)]
         */
        @Query(value = "SELECT COALESCE(c.name, '未分類') AS category_name, " +
                        "DATE(qh.answered_at) AS answer_date, " +
                        "SUM(CASE WHEN qh.is_correct = true THEN 1 ELSE 0 END) AS correct_count, " +
                        "SUM(CASE WHEN qh.is_correct = false THEN 1 ELSE 0 END) AS incorrect_count " +
                        "FROM quiz_histories qh " +
                        "JOIN words w ON qh.word_id = w.id " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE qh.user_id = :userId AND qh.answered_at >= :sinceDate " +
                        "GROUP BY c.name, DATE(qh.answered_at) " +
                        "ORDER BY category_name, answer_date", nativeQuery = true)
        List<Object[]> findDailyStatsPerCategoryByUserId(@Param("userId") Long userId,
                        @Param("sinceDate") java.time.LocalDateTime sinceDate);
}
