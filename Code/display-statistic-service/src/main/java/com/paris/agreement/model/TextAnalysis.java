package com.paris.agreement.model;

import lombok.Data;


@Data
public class TextAnalysis {
    private String word;
    private String averageWordLength;
    private String averageSentenceLength;
    private String numberOfOccurrences;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(String averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public String getAverageSentenceLength() {
        return averageSentenceLength;
    }

    public void setAverageSentenceLength(String averageSentenceLength) {
        this.averageSentenceLength = averageSentenceLength;
    }

    public String getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public void setNumberOfOccurrences(String numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
    }
}
