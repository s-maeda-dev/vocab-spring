package com.vocabulary.vocab_spring.service;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getWordsByUser(User user) {
        return wordRepository.findByUser(user);
    }

    public List<Word> getWordsByUserAndCategory(User user, Category category) {
        return wordRepository.findByUserAndCategory(user, category);
    }

    public Optional<Word> getWordByIdAndUser(Long id, User user) {
        return wordRepository.findById(id)
                .filter(word -> word.getUser().getId().equals(user.getId()));
    }

    @Transactional
    public void saveWord(Word word) {
        wordRepository.save(word);
    }

    @Transactional
    public void deleteWord(Long id, User user) {
        wordRepository.findById(id).ifPresent(word -> {
            if (word.getUser().getId().equals(user.getId())) {
                wordRepository.delete(word);
            }
        });
    }
}
