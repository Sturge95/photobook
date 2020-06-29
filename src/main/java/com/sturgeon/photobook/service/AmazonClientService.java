package com.sturgeon.photobook.service;

import java.io.File;
import java.io.IOException;

public interface AmazonClientService {
    void uploadImage(File file, String fileName, String bucketName) throws IOException;
}
