package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class DisplayStatisticService extends DisplayStatisticServiceGrpc.DisplayStatisticServiceImplBase {

    @Autowired
    private RedisStorageService redisStorageService;

    @Override
    public void displayStatistic(DisplayStatisticRequest request, StreamObserver<DisplayStatisticResponse> responseObserver) {


        List<TextAnalysis> storeTextAnalysisList = redisStorageService.getStoreTextAnalysisList();


        List<DisplayStatistic> displayStatisticList = new ArrayList<>();
        for (TextAnalysis textAnalysis : storeTextAnalysisList) {
            DisplayStatistic displayStatistic = DisplayStatistic.newBuilder()
                    .setAverageSentenceLength(textAnalysis.getAverageSentenceLength())
                    .setAverageWordLength(textAnalysis.getAverageWordLength())
                    .setWord(textAnalysis.getWord())
                    .setNumberOfOccurrences(textAnalysis.getNumberOfOccurrences())
                    .build();

            displayStatisticList.add(displayStatistic);
        }
        DisplayStatisticResponse displayStatisticResponse =
                DisplayStatisticResponse.newBuilder().addAllDisplayStatistics(displayStatisticList).build();
        responseObserver.onNext(displayStatisticResponse);
        responseObserver.onCompleted();
    }
}
