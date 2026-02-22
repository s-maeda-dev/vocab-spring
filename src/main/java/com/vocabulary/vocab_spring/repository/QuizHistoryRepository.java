package com.vocabulary.vocab_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vocabulary.vocab_spring.entity.QuizHistory;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Long> {

    @Query("SELECT qh.word.term FROM QuizHistory qh WHERE qh.user.id = :userId AND qh.isCorrect = false GROUP BY qh.word.id ORDER BY MAX(qh.answeredAt) DESC LIMIT 10")
    List<String> findRecentMistakesByUserId(@Param("userId") Long userId);
}
