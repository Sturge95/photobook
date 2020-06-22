package com.sturgeon.photobook.service.impl;

import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.repository.ImageMetaDataRepository;
import com.sturgeon.photobook.service.ImageMetaDataService;
import com.sturgeon.photobook.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageMetaDataServiceImpl implements ImageMetaDataService {

    @Autowired
    private ImageMetaDataRepository imageMetaDataRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public void saveImageMetadata(ImageMetaData metaData) {
        imageService.saveImage(metaData.getImage());
        imageMetaDataRepository.save(metaData);
    }
}
