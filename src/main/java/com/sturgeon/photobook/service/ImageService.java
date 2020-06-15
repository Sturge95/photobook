package com.sturgeon.photobook.service;

import com.sturgeon.photobook.bo.ImageUploadDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(MultipartFile multipartFile, ImageUploadDto imageUploadDto);
}
