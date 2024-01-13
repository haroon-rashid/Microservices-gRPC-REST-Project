package com.paris.agreement;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.DisplayStatistic;
import com.paris.agreement.models.DisplayStatisticRequest;
import com.paris.agreement.models.DisplayStatisticResponse;
import com.paris.agreement.models.DisplayStatisticServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@SpringJUnitConfig
public class DisplayStatisticTest {


    @GrpcClient("display-statistic-service")
    private DisplayStatisticServiceGrpc.DisplayStatisticServiceBlockingStub statisticServiceBlockingStub;


    @Test
    public void balanceTest() {
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

        System.out.println(textAnalysisList);
    }

}
