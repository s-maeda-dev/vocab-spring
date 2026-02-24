package com.vocabulary.vocab_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeakWordDto {
    private String term;
    private String definition;
    private String exampleSentence;
}
