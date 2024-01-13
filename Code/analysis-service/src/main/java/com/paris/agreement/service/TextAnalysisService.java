package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.StatisticStorageRequest;
import com.paris.agreement.models.StatisticStorageServiceGrpc;
import com.paris.agreement.stream.StreamStatisticStorageResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TextAnalysisService {

    @Value("#{${choiceWords.map}}")
    Map<String, Float> choiceWords;

    @Autowired
    private StatisticStorageClient statisticStorageClient;

    public void textForAnalysis(StringBuilder stringBuilder) {

        List<String> filterSentence = convertToFilterSentences(stringBuilder);
        Map<String, Float> noOfOccurrences = getMapWords();
        Map<String, Float> avgSentences = getMapWords();

        for (String key : avgSentences.keySet()) {
            AtomicInteger sentenceLength = new AtomicInteger();
            AtomicInteger sentenceCounter = new AtomicInteger();
            filterSentence.stream().map(x -> x.toLowerCase()).filter(x -> x.equals(key.toLowerCase()) || x.contains(key.toLowerCase())).forEach(
                    x -> {
                        StringTokenizer t = new StringTokenizer(x);
                        while (t.hasMoreTokens()) {
                            String word = t.nextToken();
                            if (word.equals(key.toLowerCase()) || word.contains(key.toLowerCase())) {
                                noOfOccurrences.computeIfPresent(key, (k, v) -> v + 1);
                            }
                            sentenceLength.getAndIncrement();
                        }
                        sentenceCounter.getAndIncrement();
                    }
            );
            avgSentences.computeIfPresent(key, (k, v) -> Float.valueOf(sentenceLength.get()) / Float.valueOf(sentenceCounter.get()));
            sentenceLength.set(0);
            sentenceCounter.set(0);
        }


        List<TextAnalysis> textAnalysisList = convertToTextAnalysis(avgSentences, noOfOccurrences);

        System.out.println(textAnalysisList);
        statisticStorageClient.statisticClient(textAnalysisList);
    }

    private List<TextAnalysis> convertToTextAnalysis(Map<String, Float> map1, Map<String, Float> map2) {
        List<TextAnalysis> textAnalysisList = new ArrayList<>();


        for (Map.Entry entry : map1.entrySet()) {
            TextAnalysis textAnalysis = new TextAnalysis();
            textAnalysis.setWord(entry.getKey().toString());
            textAnalysis.setAverageSentenceLength(entry.getValue().toString());
            textAnalysisList.add(textAnalysis);
        }

        for (Map.Entry<String, Float> entry : map2.entrySet()) {
            textAnalysisList.stream().filter(x -> x.getWord().equals(entry.getKey())).map(x ->
            {
                x.setNumberOfOccurrences(entry.getValue().toString());
                x.setAverageWordLength(String.valueOf(x.getWord().length()));
                return x;
            }).collect(Collectors.toList());
        }
        return textAnalysisList;
    }


    private Map<String, Float> getMapWords() {
        Map<String, Float> map = new HashMap<>();
        map.put("Food", 0F);
        map.put("Technology", 0F);
        map.put("Deforestation", 0F);
        map.put("Socioeconomic", 0F);
        map.put("Ecological", 0F);
        map.put("Diversification", 0F);
        map.put("Sustain", 0F);
        map.putAll(choiceWords);
        return map;
    }


    private List<String> convertToFilterSentences(StringBuilder stringBuilder) {

        List<String> sentences = new ArrayList<>();
        Pattern re = Pattern.compile(
                "[^.!?\\s] \n" +
                        "[^.!?]* \n" +
                        "(?: \n" +
                        "  [.!?]\n" +
                        "  (?!['\"]?\\s|$) \n" +
                        "  [^.!?]* \n" +
                        ")*\n" +
                        "[.!?]?\n" +
                        "['\"]?\n" +
                        "(?=\\s|$)",
                Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher reMatcher = re.matcher(stringBuilder);
        while (reMatcher.find()) {
            sentences.add(reMatcher.group());
        }
        List<String> stringList = sentences.stream().map(s -> {
            s = s.replaceAll("\\p{Punct}", "");
            s = s.replaceAll("\\d", "");
            return s;
        }).collect(Collectors.toList());
        List<String> filterSentence = stringList.stream().filter(x -> !x.isEmpty() && !x.isBlank()).collect(Collectors.toList());

        return filterSentence;
    }


}
