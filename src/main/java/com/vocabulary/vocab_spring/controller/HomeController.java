package com.vocabulary.vocab_spring.controller;

import com.vocabulary.vocab_spring.entity.Category;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.service.CategoryService;
import com.vocabulary.vocab_spring.service.UserService;
import com.vocabulary.vocab_spring.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final UserService userService;
    private final WordService wordService;

    @Autowired
    public HomeController(CategoryService categoryService, UserService userService, WordService wordService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            User user = userService.findByUsername(userDetails.getUsername());
            List<Category> categories = categoryService.getCategoriesByUser(user);

            // カテゴリーごとの単語数を一括取得
            List<Object[]> counts = wordService.getWordCountPerCategory(user);
            Map<String, Long> wordCounts = counts.stream()
                    .collect(Collectors.toMap(
                            obj -> (String) obj[0],
                            obj -> (Long) obj[1],
                            (v1, v2) -> v1 // キー重複時は最初を優先（念のため）
                    ));

            // カテゴリーと単語数をペアにした DTO 相当のリマップ
            List<Map<String, Object>> categoryListWithStats = categories.stream().map(cat -> {
                Map<String, Object> map = new java.util.HashMap<>();
                map.put("id", cat.getId());
                map.put("name", cat.getName());
                map.put("count", wordCounts.getOrDefault(cat.getName(), 0L));
                return map;
            }).collect(Collectors.toList());

            model.addAttribute("categories", categoryListWithStats);
        }
        return "home";
    }
}
