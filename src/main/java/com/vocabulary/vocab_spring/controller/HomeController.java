package com.vocabulary.vocab_spring.controller;

import com.vocabulary.vocab_spring.dto.TotalStatsDto;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.service.StatsService;
import com.vocabulary.vocab_spring.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final StatsService statsService;
    private final SecurityUtils securityUtils;

    @Autowired
    public HomeController(StatsService statsService, SecurityUtils securityUtils) {
        this.statsService = statsService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/")
    public String home(Model model) {
        User user = securityUtils.getCurrentUser();
        if (user != null) {
            TotalStatsDto stats = statsService.getTotalStats(user);
            model.addAttribute("totalStats", stats);
        }
        return "home";
    }
}
