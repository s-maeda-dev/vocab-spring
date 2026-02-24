package com.vocabulary.vocab_spring.service;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesByUser(User user) {
        return categoryRepository.findByUser(user);
    }

    public java.util.Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public void addCategory(String name, User user) {
        Category category = new Category();
        category.setName(name);
        category.setUser(user);
        categoryRepository.save(category);
    }

    @Autowired
    private com.vocabulary.vocab_spring.repository.WordRepository wordRepository;

    @Transactional
    public void updateCategory(Long id, String name, User user) {
        categoryRepository.findById(id).ifPresent(category -> {
            if (category.getUser().getId().equals(user.getId())) {
                category.setName(name);
                categoryRepository.save(category);
            }
        });
    }

    @Transactional
    public void deleteCategory(Long id, User user) {
        categoryRepository.findById(id).ifPresent(category -> {
            if (category.getUser().getId().equals(user.getId())) {
                List<com.vocabulary.vocab_spring.entity.Word> words = wordRepository.findByUserAndCategory(user,
                        category);
                for (com.vocabulary.vocab_spring.entity.Word w : words) {
                    w.setCategory(null);
                    wordRepository.save(w);
                }
                categoryRepository.delete(category);
            }
        });
    }
}
