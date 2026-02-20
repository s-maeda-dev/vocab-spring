package com.vocabulary.vocab_spring.repository;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByUser(User user);

    List<Word> findByUserAndCategory(User user, Category category);
}
