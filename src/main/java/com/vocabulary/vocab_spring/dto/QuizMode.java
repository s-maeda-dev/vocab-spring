package com.vocabulary.vocab_spring.dto;

/**
 * クイズの出題モードを表す列挙型（enum）。
 * 許可される値を限定することで、タイプミスによるバグを防ぐ。
 *
 * - ALL: 登録されている全単語から出題
 * - WEAK: 過去に間違えた苦手単語のみから出題
 */
public enum QuizMode {
    ALL,
    WEAK;

    /**
     * フォームから送信される小文字の値（"all", "weak"）を enum に変換する。
     * Spring MVC の @RequestParam で自動変換されるよう、大文字変換して標準の valueOf を呼ぶ。
     *
     * @param value フォームからの送信値（例: "all", "weak"）
     * @return 対応する QuizMode
     * @throws IllegalArgumentException 未知の値が渡された場合
     */
    public static QuizMode fromString(String value) {
        if (value == null || value.isBlank()) {
            return ALL;
        }
        return valueOf(value.toUpperCase());
    }
}
