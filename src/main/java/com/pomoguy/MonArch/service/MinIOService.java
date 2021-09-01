package com.pomoguy.MonArch.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;

public class MinIOService {

    private final MinioClient minioClient;

    @Autowired
    public MinIOService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public HashMap<String,String> upload(MultipartFile file, String fileKey) {
        String bucketName = "monarch";
        String fileName = file.getOriginalFilename();

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(),file.getSize(),-1)
                            .contentType(file.getContentType())
                            .build());

        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<String, String>();
        String fileUrl = bucketName + "/" + fileName;
        map.put(fileKey,fileUrl);
        return map;
    }

}
