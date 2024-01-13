package com.paris.agreement.service;

import com.paris.agreement.models.FileReaderRequest;
import com.paris.agreement.models.FileReaderResponse;
import com.paris.agreement.models.UploadFileReaderServiceGrpc;
import com.paris.agreement.stream.StreamFileReaderRequest;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class FileReaderService extends UploadFileReaderServiceGrpc.UploadFileReaderServiceImplBase {

    @Autowired
    private TextAnalysisService textAnalysisService;

    public StreamObserver<FileReaderRequest> fileReader(StreamObserver<FileReaderResponse> responseObserver) {
        return new StreamFileReaderRequest(responseObserver, textAnalysisService);
    }
}
