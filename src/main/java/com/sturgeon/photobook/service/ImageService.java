package com.sturgeon.photobook.service;

import com.sturgeon.photobook.bo.Image;
import com.sturgeon.photobook.bo.ImageUploadDto;

public interface ImageService {
    void uploadImage(ImageUploadDto imageUploadDto);

    void saveImage(Image image);
}
