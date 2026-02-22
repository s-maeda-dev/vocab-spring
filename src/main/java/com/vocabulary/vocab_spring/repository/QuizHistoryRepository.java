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
     *
     * @param userId   ログイン中のユーザーID
     * @param pageable 件数制限などのページング情報（例: PageRequest.of(0, 10)）
     * @return 間違えた単語の term（単語名）リスト
     */
    @Query("SELECT qh.word.term FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false GROUP BY qh.word.id ORDER BY MAX(qh.answeredAt) DESC")
    List<String> findRecentMistakesByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * ユーザーが苦手な単語（Wordエンティティ）を不正解回数の多い順・最近間違えた順で取得する。
     * 苦手問題出題機能で使用する。
     *
     * @param userId   ログイン中のユーザーID
     * @param pageable 件数制限などのページング情報（例: PageRequest.of(0, 50)）
     * @return 苦手なWordエンティティのリスト
     */
    @Query("SELECT qh.word FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false GROUP BY qh.word.id ORDER BY COUNT(qh.id) DESC, MAX(qh.answeredAt) DESC")
    List<Word> findWeakWordsByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * ユーザーが不正解になったことのある単語（苦手単語）の件数を取得する。
     *
     * @param userId ログイン中のユーザーID
     * @return 苦手単語のユニーク件数
     */
    @Query("SELECT COUNT(DISTINCT qh.word.id) FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false")
    long countWeakWordsByUserId(@Param("userId") Long userId);
}
