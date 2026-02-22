package com.vocabulary.vocab_spring.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vocabulary.vocab_spring.entity.QuizHistory;
import com.vocabulary.vocab_spring.entity.Word;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Long> {

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
    // 苦手単語: カテゴリ絞り込みあり
    // ─────────────────────────────────────────────

    /**
     * ユーザーが苦手な単語（Wordエンティティ）を、指定カテゴリに絞って取得する。
     * カテゴリIDで絞り込み + 不正解回数の多い順・最近間違えた順。
     */
    @Query("SELECT qh.word FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false AND qh.word.category.id = :categoryId GROUP BY qh.word.id ORDER BY COUNT(qh.id) DESC, MAX(qh.answeredAt) DESC")
    List<Word> findWeakWordsByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId,
            Pageable pageable);

    /**
     * ユーザーが不正解になったことのある単語（苦手単語）のユニーク件数。
     * カテゴリIDで絞り込みあり。
     */
    @Query("SELECT COUNT(DISTINCT qh.word.id) FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false AND qh.word.category.id = :categoryId")
    long countWeakWordsByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
