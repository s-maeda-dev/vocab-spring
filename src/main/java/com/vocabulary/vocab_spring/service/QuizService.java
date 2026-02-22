package com.vocabulary.vocab_spring.service;

import com.vocabulary.vocab_spring.entity.QuizHistory;
import com.vocabulary.vocab_spring.repository.QuizHistoryRepository;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final WordRepository wordRepository;
    private final QuizHistoryRepository quizHistoryRepository;

    @Autowired
    public QuizService(WordRepository wordRepository, QuizHistoryRepository quizHistoryRepository) {
        this.wordRepository = wordRepository;
        this.quizHistoryRepository = quizHistoryRepository;
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

    public void saveQuizHistory(User user, Word word, boolean isCorrect) {
        QuizHistory history = new QuizHistory();
        history.setUser(user);
        history.setWord(word);
        history.setCorrect(isCorrect);
        quizHistoryRepository.save(history);
    }
}
