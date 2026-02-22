package com.vocabulary.vocab_spring.repository;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

        List<Word> findByUser(User user);

        List<Word> findByUserAndCategory(User user, Category category);

        // ─────────────────────────────────────────────
        // ランダム出題
        // ─────────────────────────────────────────────

        @Query(value = "SELECT * FROM words WHERE user_id = :userId ORDER BY RAND() LIMIT 1", nativeQuery = true)
        Word findRandomWordByUserId(@Param("userId") Long userId);

        @Query(value = "SELECT * FROM words WHERE user_id = :userId AND category_id = :categoryId ORDER BY RAND() LIMIT 1", nativeQuery = true)
        Word findRandomWordByUserIdAndCategoryId(@Param("userId") Long userId,
                        @Param("categoryId") Long categoryId);

        @Query(value = "SELECT * FROM words WHERE user_id = :userId AND id NOT IN :excludedIds ORDER BY RAND() LIMIT 1", nativeQuery = true)
        Word findRandomWordByUserIdWithExclusion(@Param("userId") Long userId,
                        @Param("excludedIds") Collection<Long> excludedIds);

        @Query(value = "SELECT * FROM words WHERE user_id = :userId AND category_id = :categoryId AND id NOT IN :excludedIds ORDER BY RAND() LIMIT 1", nativeQuery = true)
        Word findRandomWordByUserIdAndCategoryIdWithExclusion(@Param("userId") Long userId,
                        @Param("categoryId") Long categoryId,
                        @Param("excludedIds") Collection<Long> excludedIds);

        // ─────────────────────────────────────────────
        // 件数カウント
        // ─────────────────────────────────────────────

        long countByUserId(Long userId);

        long countByUserIdAndCategoryId(Long userId, Long categoryId);

        /**
         * カテゴリ名でフィルタして登録単語数を返す（単一カテゴリ）。
         * COALESCE で category_id が null の単語を "未分類" として扱う。
         */
        @Query(value = "SELECT COUNT(*) FROM words w " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE w.user_id = :userId AND COALESCE(c.name, '未分類') = :categoryName", nativeQuery = true)
        long countByUserIdAndCategoryName(@Param("userId") Long userId,
                        @Param("categoryName") String categoryName);

        /**
         * ユーザーの全カテゴリの登録単語数を一括取得する（N+1問題対策）。
         * 戻り値は Object[][]: [カテゴリ名(String), 単語数(Long)]
         * 統計画面のカテゴリ別表示で使用する。
         */
        @Query(value = "SELECT COALESCE(c.name, '未分類') AS category_name, COUNT(*) AS word_count " +
                        "FROM words w " +
                        "LEFT JOIN categories c ON w.category_id = c.id " +
                        "WHERE w.user_id = :userId " +
                        "GROUP BY c.name", nativeQuery = true)
        List<Object[]> findWordCountPerCategoryByUserId(@Param("userId") Long userId);
}
