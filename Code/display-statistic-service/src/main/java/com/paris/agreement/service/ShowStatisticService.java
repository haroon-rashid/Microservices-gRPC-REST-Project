package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.DisplayStatistic;
import com.paris.agreement.models.DisplayStatisticRequest;
import com.paris.agreement.models.DisplayStatisticResponse;
import com.paris.agreement.models.DisplayStatisticServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowStatisticService {

    @GrpcClient("display-statistic-service")
    private DisplayStatisticServiceGrpc.DisplayStatisticServiceBlockingStub statisticServiceBlockingStub;

    public List<TextAnalysis> getStatisticData() {
        List<TextAnalysis> textAnalysisList = new ArrayList<>();

        DisplayStatisticRequest displayStatisticRequest = DisplayStatisticRequest.newBuilder().setMessage("Message").build();
        DisplayStatisticResponse displayStatisticResponse = statisticServiceBlockingStub.displayStatistic(displayStatisticRequest);

        List<DisplayStatistic> displayStatisticsList = displayStatisticResponse.getDisplayStatisticsList();

        displayStatisticsList.forEach(ds ->
        {
            TextAnalysis textAnalysis = new TextAnalysis();
            textAnalysis.setAverageSentenceLength(ds.getAverageSentenceLength());
            textAnalysis.setWord(ds.getWord());
            textAnalysis.setNumberOfOccurrences(ds.getNumberOfOccurrences());
            textAnalysis.setAverageWordLength(ds.getAverageWordLength());
            textAnalysisList.add(textAnalysis);
        });


        return textAnalysisList;
    }


}
