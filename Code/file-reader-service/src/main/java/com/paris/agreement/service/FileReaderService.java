package com.paris.agreement.service;

import com.paris.agreement.model.SteamFileReaderResponse;
import com.paris.agreement.models.FileReaderRequest;
import com.paris.agreement.models.FileReaderResponse;
import com.paris.agreement.models.UploadFileReaderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FileReaderService {

    @Value("${complete.file.path}")
    private String FILE_COMPLETE_PATH;


    @GrpcClient("file-reader-service")
    private UploadFileReaderServiceGrpc.UploadFileReaderServiceStub fileReaderServiceStub;

    public void fileReader() throws IOException {

        System.out.println("FILE_COMPLETE_PATH : " + FILE_COMPLETE_PATH);
        if (FILE_COMPLETE_PATH != null && !FILE_COMPLETE_PATH.isEmpty() && !FILE_COMPLETE_PATH.isBlank()) {
            Path path = Paths.get(FILE_COMPLETE_PATH);

            String fileData = Files.readString(path);
            StringTokenizer tokenizer = new StringTokenizer(fileData);

            StreamObserver<FileReaderRequest> fileReaderRequestStreamObserver = fileReaderServiceStub.fileReader(new SteamFileReaderResponse());
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                FileReaderRequest fileReaderRequest = FileReaderRequest.newBuilder().setStringWord(" " + word).build();
                fileReaderRequestStreamObserver.onNext(fileReaderRequest);
            }
            fileReaderRequestStreamObserver.onCompleted();

        } else {
            System.out.println("==>> FILE PATH IS EMPTY <<==");
        }
    }


}
