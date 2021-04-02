package com.app.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.app.exception.AppException;
import com.app.model.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AmazonService {

    private AmazonS3 s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.folderName}")
    private String folderName;

    @PostConstruct
    public void init() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        log.info("BUCKETS: {}", getAllBuckets());
        log.info("FILES: {}", getAllFiles());

    }

    // -----------------------------------------------------------------------------
    // METODY POZWALAJACE SPRAWDZIC CZY POLACZENIE SIE POWIODLO
    // -----------------------------------------------------------------------------
    private String getAllBuckets() {
        return s3Client
                .listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.joining(","));
    }

    private String getAllFiles() {
        return s3Client
                .listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.joining(","));
    }

    public String uploadFile(MultipartFile fileStorage) {
        System.out.println(fileStorage);
        if (fileStorage == null) {
            throw new IllegalArgumentException("upload file exception");
        }

        String filename = generateFilenames(fileStorage);
        File file = convertMultipartFileToFile(fileStorage);
        s3Client.putObject(bucketName, folderName + "/" + filename, file);
        deleteTempFile(file);
        return endpointUrl + filename;
    }

    // -----------------------------------------------------------------------------
    // METODY POMOCNICZE PRZY UPLOADZIE PLIKOW
    // -----------------------------------------------------------------------------

    private String generateFilenames(MultipartFile multipartFile) {
        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
        String filename1 = UUID.randomUUID().toString().replaceAll("\\W", "");
        String filename2 = String.valueOf(System.nanoTime());
        return filename1 + "-" + filename2 + "." + extension;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        try {
            File convFile = new File(multipartFile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();
            return convFile;
        } catch (Exception e) {
            throw new AppException("convert multipart file to file exception");
        }
    }

    private boolean deleteTempFile(File file) {
        if (file == null) {
            throw new AppException("delete temp file exception");
        }
        return file.delete();
    }
}
