package com.vocabulary.vocab_spring.controller;

import java.util.List;

import com.vocabulary.vocab_spring.dto.CategoryDetailStatsDto;
import com.vocabulary.vocab_spring.dto.TotalStatsDto;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.service.StatsService;
import com.vocabulary.vocab_spring.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学習統計画面を表示するController。
 * ユーザーの学習履歴をカテゴリ別にグラフや数値で可視化する。
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final SecurityUtils securityUtils;

    @Autowired
    public StatsController(StatsService statsService, SecurityUtils securityUtils) {
        this.statsService = statsService;
        this.securityUtils = securityUtils;
    }

    /**
     * GET /stats — 学習統計画面を表示する。
     * 累計統計（ヘッダー用）とカテゴリ別詳細統計（直近14日間）を取得してテンプレートに渡す。
     */
    @GetMapping
    public String showStats(Model model) {
        User user = securityUtils.getCurrentUser();

        // 累計統計（上部サマリーカード用）
        TotalStatsDto totalStats = statsService.getTotalStats(user);
        model.addAttribute("totalStats", totalStats);

        // カテゴリ別詳細統計（カテゴリごとのセクション表示用・直近14日間）
        List<CategoryDetailStatsDto> categoryDetailStats = statsService.getCategoryDetailStats(user, 14);
        model.addAttribute("categoryDetailStats", categoryDetailStats);

        return "stats";
    }
}
