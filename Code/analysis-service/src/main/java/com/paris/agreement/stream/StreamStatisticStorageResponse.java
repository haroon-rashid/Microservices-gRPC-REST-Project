package com.paris.agreement.stream;

import com.paris.agreement.models.StatisticStorageResponse;
import io.grpc.stub.StreamObserver;

public class StreamStatisticStorageResponse implements StreamObserver<StatisticStorageResponse> {
    @Override
    public void onNext(StatisticStorageResponse statisticStorageResponse) {

        System.out.println("STATUS : " + statisticStorageResponse.getMessage());

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
