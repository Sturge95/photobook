package com.sturgeon.photobook.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sturgeon.photobook.service.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AmazonClientServiceImpl implements AmazonClientService {

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public void uploadImage(MultipartFile multipartFile, String fileName, String bucketName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);

        s3Client.putObject(putObjectRequest);
        file.delete();
    }
}
