package com.paris.agreement.stream;

import com.paris.agreement.models.FileReaderRequest;
import com.paris.agreement.models.FileReaderResponse;
import com.paris.agreement.service.TextAnalysisService;
import io.grpc.stub.StreamObserver;

public class StreamFileReaderRequest implements StreamObserver<FileReaderRequest> {


    private StreamObserver<FileReaderResponse> fileReaderResponseStreamObserver;
    private TextAnalysisService textAnalysisService;

    public StreamFileReaderRequest(StreamObserver<FileReaderResponse> fileReaderResponseStreamObserver, TextAnalysisService textAnalysisService) {
        this.fileReaderResponseStreamObserver = fileReaderResponseStreamObserver;
        this.textAnalysisService = textAnalysisService;
    }

    StringBuilder textFileData = new StringBuilder();

    @Override
    public void onNext(FileReaderRequest fileReaderRequest) {
        String word = fileReaderRequest.getStringWord();
        textFileData.append(word);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        FileReaderResponse fileReaderResponse = FileReaderResponse.newBuilder().setMessage("Data Are Received Successfully").build();
        fileReaderResponseStreamObserver.onNext(fileReaderResponse);
        textAnalysisService.textForAnalysis(textFileData);
        fileReaderResponseStreamObserver.onCompleted();


    }
}
