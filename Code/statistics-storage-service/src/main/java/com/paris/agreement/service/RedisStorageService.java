package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.repository.TextAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisStorageService {

    @Autowired
    private TextAnalysisRepository textAnalysisRepository;

    public void storeTextAnalysis(List<TextAnalysis> textAnalysisList) {
        System.out.println(textAnalysisList);
        textAnalysisRepository.saveAll(textAnalysisList);
    }


    public List<TextAnalysis> getStoreTextAnalysisList() {
      return (List<TextAnalysis>) textAnalysisRepository.findAll();
    }


}
