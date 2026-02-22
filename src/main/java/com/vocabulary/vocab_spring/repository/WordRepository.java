package com.vocabulary.vocab_spring.repository;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByUser(User user);

    List<Word> findByUserAndCategory(User user, Category category);

    @org.springframework.data.jpa.repository.Query(value = "SELECT * FROM words WHERE user_id = :userId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWordByUserId(@org.springframework.data.repository.query.Param("userId") Long userId);

    @org.springframework.data.jpa.repository.Query(value = "SELECT * FROM words WHERE user_id = :userId AND category_id = :categoryId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWordByUserIdAndCategoryId(@org.springframework.data.repository.query.Param("userId") Long userId,
            @org.springframework.data.repository.query.Param("categoryId") Long categoryId);

    long countByUserId(Long userId);

    long countByUserIdAndCategoryId(Long userId, Long categoryId);

    @org.springframework.data.jpa.repository.Query(value = "SELECT * FROM words WHERE user_id = :userId AND id NOT IN :excludedIds ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWordByUserIdWithExclusion(@org.springframework.data.repository.query.Param("userId") Long userId,
            @org.springframework.data.repository.query.Param("excludedIds") java.util.Collection<Long> excludedIds);

    @org.springframework.data.jpa.repository.Query(value = "SELECT * FROM words WHERE user_id = :userId AND category_id = :categoryId AND id NOT IN :excludedIds ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWordByUserIdAndCategoryIdWithExclusion(
            @org.springframework.data.repository.query.Param("userId") Long userId,
            @org.springframework.data.repository.query.Param("categoryId") Long categoryId,
            @org.springframework.data.repository.query.Param("excludedIds") java.util.Collection<Long> excludedIds);
}
