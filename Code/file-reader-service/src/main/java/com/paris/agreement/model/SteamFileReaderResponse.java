package com.paris.agreement.model;

import com.paris.agreement.models.FileReaderResponse;
import io.grpc.stub.StreamObserver;

public class SteamFileReaderResponse implements StreamObserver<FileReaderResponse> {
    @Override
    public void onNext(FileReaderResponse fileReaderResponse) {
        System.out.println("Status : "+fileReaderResponse.getMessage());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
