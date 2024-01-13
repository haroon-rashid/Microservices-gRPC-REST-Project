package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.StatisticStorage;
import com.paris.agreement.models.StatisticStorageRequest;
import com.paris.agreement.models.StatisticStorageResponse;
import com.paris.agreement.models.StatisticStorageServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class StatisticStorageService extends StatisticStorageServiceGrpc.StatisticStorageServiceImplBase {

    @Autowired
    private RedisStorageService redisStorageService;


    @Override
    public void storeStatistic(StatisticStorageRequest request, StreamObserver<StatisticStorageResponse> responseObserver) {

        List<StatisticStorage> statisticStoragesList = request.getStatisticStoragesList();
        List<TextAnalysis> textAnalysisList = new ArrayList<>();
        statisticStoragesList.stream().forEach(statisticStorage -> {
            TextAnalysis textAnalysis = new TextAnalysis();
            textAnalysis.setAverageSentenceLength(statisticStorage.getAverageSentenceLength());
            textAnalysis.setWord(statisticStorage.getWord());
            textAnalysis.setNumberOfOccurrences(statisticStorage.getNumberOfOccurrences());
            textAnalysis.setAverageWordLength(statisticStorage.getAverageWordLength());
            textAnalysisList.add(textAnalysis);
        });

        redisStorageService.storeTextAnalysis(textAnalysisList);

        StatisticStorageResponse storageResponse = StatisticStorageResponse.newBuilder().setMessage("DATA STORE SUCCESSFULLY").build();
        responseObserver.onNext(storageResponse);
        responseObserver.onCompleted();

    }
}
