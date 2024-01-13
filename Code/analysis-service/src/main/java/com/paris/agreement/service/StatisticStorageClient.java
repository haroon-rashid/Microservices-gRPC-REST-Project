package com.paris.agreement.service;

import com.paris.agreement.model.TextAnalysis;
import com.paris.agreement.models.StatisticStorage;
import com.paris.agreement.models.StatisticStorageRequest;
import com.paris.agreement.models.StatisticStorageResponse;
import com.paris.agreement.models.StatisticStorageServiceGrpc;
import com.paris.agreement.stream.StreamStatisticStorageResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticStorageClient {


    @GrpcClient("statistic-storage-service")
    private StatisticStorageServiceGrpc.StatisticStorageServiceBlockingStub statisticStorageServiceBlockingStub;


    public void statisticClient(List<TextAnalysis> textAnalysisList) {
        List<StatisticStorage> statisticStorageList = new ArrayList<>();
        for (TextAnalysis analysis : textAnalysisList) {
            StatisticStorage statisticStorage = StatisticStorage.newBuilder()
                    .setAverageSentenceLength(analysis.getAverageSentenceLength())
                    .setAverageWordLength(analysis.getAverageWordLength())
                    .setNumberOfOccurrences(analysis.getNumberOfOccurrences())
                    .setWord(analysis.getWord())
                    .build();
            statisticStorageList.add(statisticStorage);

        }

        StatisticStorageRequest statisticStorageRequest = StatisticStorageRequest.newBuilder().addAllStatisticStorages(statisticStorageList).build();
        StatisticStorageResponse storageResponse = statisticStorageServiceBlockingStub.storeStatistic(statisticStorageRequest);
        System.out.println("STATUS : " + storageResponse.getMessage());

    }

}
