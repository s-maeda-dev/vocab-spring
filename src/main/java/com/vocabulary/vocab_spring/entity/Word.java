package com.vocabulary.vocab_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 単語・用語 (カンマ区切りで複数表記可能)
    // 例: "Apple" or "Dependency Injection, 依存性の注入"
    private String term;

    // 意味・定義・翻訳
    // 例: "りんご" or "依存オブジェクトを外部から渡すデザインパターン"
    private String definition;

    // 例文
    private String exampleSentence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @jakarta.persistence.OneToMany(mappedBy = "word", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private java.util.List<QuizHistory> quizHistories = new java.util.ArrayList<>();
}
