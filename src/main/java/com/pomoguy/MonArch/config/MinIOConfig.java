package com.pomoguy.MonArch.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
public class MinIOConfig {

    @Value("${spring.minio.url}")
    private String url;

    @Value("${spring.minio.bucket}")
    private String bucketName;

    @Value("${spring.minio.access-key}")
    private String accessKey;

    @Value("${spring.minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:9001")
                .credentials("admin", "password")
                .build();
        minioClient.setTimeout(Duration.ofSeconds(10).toMillis(),
                Duration.ofSeconds(60).toMillis(),
                Duration.ofSeconds(10).toMillis());

       // if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket("monarch").build())) {
         //   minioClient.makeBucket(MakeBucketArgs.builder().bucket("monarch").build());
       // }

        return minioClient;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
