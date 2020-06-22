package com.sturgeon.photobook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AmazonClientService {

    void uploadImage(MultipartFile multipartFile, String fileName, String bucketName) throws IOException;
}
