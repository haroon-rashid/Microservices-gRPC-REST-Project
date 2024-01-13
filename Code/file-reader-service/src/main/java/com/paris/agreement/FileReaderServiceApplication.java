package com.paris.agreement;

import com.paris.agreement.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileReaderServiceApplication implements CommandLineRunner {

    @Autowired
    private FileReaderService fileReaderService;

    public static void main(String[] args) {
        SpringApplication.run(FileReaderServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//
//        String str = "String - is a squence of chars:~!@#$%^&*(). Test.";
//        System.out.println(str);
//        String result = str.replaceAll("\\p{Punct}", "");
//        System.out.println(result);
        fileReaderService.fileReader();
    }
}
