package com.vocabulary.vocab_spring.service;

import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final WordRepository wordRepository;

    @Autowired
    public QuizService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getRandomWord(User user, java.util.Collection<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            return wordRepository.findRandomWordByUserId(user.getId());
        }
        return wordRepository.findRandomWordByUserIdWithExclusion(user.getId(), excludedIds);
    }

    public Word getRandomWordByCategory(User user, Long categoryId, java.util.Collection<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            return wordRepository.findRandomWordByUserIdAndCategoryId(user.getId(), categoryId);
        }
        return wordRepository.findRandomWordByUserIdAndCategoryIdWithExclusion(user.getId(), categoryId, excludedIds);
    }

    public long getWordCount(User user) {
        return wordRepository.countByUserId(user.getId());
    }

    public long getWordCountByCategory(User user, Long categoryId) {
        return wordRepository.countByUserIdAndCategoryId(user.getId(), categoryId);
    }
}
