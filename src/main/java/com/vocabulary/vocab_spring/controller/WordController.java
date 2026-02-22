package com.vocabulary.vocab_spring.controller;

import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.UserRepository;
import com.vocabulary.vocab_spring.service.CategoryService;
import com.vocabulary.vocab_spring.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/words")
public class WordController {

        private final WordService wordService;
        private final CategoryService categoryService;
        private final UserRepository userRepository;

        @Autowired
        public WordController(WordService wordService, CategoryService categoryService, UserRepository userRepository) {
                this.wordService = wordService;
                this.categoryService = categoryService;
                this.userRepository = userRepository;
        }

        @GetMapping
        public String listWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                List<Word> words = wordService.getWordsByUser(user);

                // カテゴリ別にグループ化（LinkedHashMap でソート順を保持）
                Map<String, List<Word>> wordsByCategory = words.stream()
                                .collect(Collectors.groupingBy(
                                                w -> w.getCategory() != null ? w.getCategory().getName() : "未分類",
                                                LinkedHashMap::new,
                                                Collectors.toList()));

                // 「未分類」を最後に移動するためソートし直す
                Map<String, List<Word>> sorted = new LinkedHashMap<>();
                wordsByCategory.entrySet().stream()
                                .sorted((a, b) -> {
                                        if ("未分類".equals(a.getKey()))
                                                return 1;
                                        if ("未分類".equals(b.getKey()))
                                                return -1;
                                        return a.getKey().compareTo(b.getKey());
                                })
                                .forEach(e -> sorted.put(e.getKey(), e.getValue()));

                model.addAttribute("wordsByCategory", sorted);
                return "word_list";
        }

        @GetMapping("/new")
        public String showAddForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                model.addAttribute("word", new Word());
                model.addAttribute("categories", categoryService.getCategoriesByUser(user));
                return "word_form";
        }

        @PostMapping
        public String saveWord(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Word word) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                word.setUser(user);
                wordService.saveWord(word);
                return "redirect:/words";
        }

        @GetMapping("/edit/{id}")
        public String showEditForm(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id,
                        Model model) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Word word = wordService.getWordByIdAndUser(id, user)
                                .orElseThrow(() -> new RuntimeException("Word not found"));

                model.addAttribute("word", word);
                model.addAttribute("categories", categoryService.getCategoriesByUser(user));
                return "word_form";
        }

        @PostMapping("/update/{id}")
        public String updateWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id,
                        @ModelAttribute Word word) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Ensure the word belongs to the user before updating
                wordService.getWordByIdAndUser(id, user)
                                .orElseThrow(() -> new RuntimeException("Word not found"));

                word.setId(id);
                word.setUser(user);
                wordService.saveWord(word);
                return "redirect:/words";
        }

        @PostMapping("/delete/{id}")
        public String deleteWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
                User user = userRepository.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                wordService.deleteWord(id, user);
                return "redirect:/words";
        }
}
