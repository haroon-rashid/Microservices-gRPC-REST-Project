package com.paris.agreement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;

@Data
@TypeAlias("TextAnalysis")
@RedisHash("TextAnalysis")
public class TextAnalysis {
    @Id
    private String word;
    private String averageWordLength;
    private String averageSentenceLength;
    private String numberOfOccurrences;
}
