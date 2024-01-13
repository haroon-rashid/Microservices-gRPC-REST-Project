package com.paris.agreement.model;

import lombok.Data;

@Data
public class TextAnalysis {
    private String word;
    private String averageWordLength;
    private String averageSentenceLength;
    private String numberOfOccurrences;



}
