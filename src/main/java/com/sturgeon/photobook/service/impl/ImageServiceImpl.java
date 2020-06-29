package com.sturgeon.photobook.service.impl;

import com.drew.imaging.ImageProcessingException;
import com.sturgeon.photobook.bo.Image;
import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.bo.ImageUploadDto;
import com.sturgeon.photobook.repository.ImageRepository;
import com.sturgeon.photobook.service.ImageCompressionService;
import com.sturgeon.photobook.service.ImageMetaDataService;
import com.sturgeon.photobook.service.ImageService;
import com.sturgeon.photobook.util.FileUtils;
import com.sturgeon.photobook.util.MetaDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);


    @Value("${s3.buckets.compressed}")
    private String compressedBucket;
    @Value("${s3.buckets.uncompressed}")
    private String unCompressedBucket;

    @Autowired
    private AmazonClientServiceImpl amazonClientService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMetaDataService imageMetaDataService;
    @Autowired
    private ImageCompressionService imageCompressionService;

    @Override
    public void uploadImage(ImageUploadDto imageUploadDto) {
        try {
            MultipartFile multipartImageFile = imageUploadDto.getImage();

            Image image = createImage(imageUploadDto);
            ImageMetaData imageMetaData = createImageMetadata(multipartImageFile, image);

            File imageFile = FileUtils.convert(multipartImageFile);
            File compressedImage = imageCompressionService.compressImage(imageFile, imageMetaData.getFileExtension());

            amazonClientService.uploadImage(imageFile, image.getFileName(), unCompressedBucket);
            amazonClientService.uploadImage(compressedImage, image.getFileName(), compressedBucket);

            imageMetaDataService.saveImageMetadata(imageMetaData);
        } catch (IOException | ImageProcessingException e) {
            logger.error("could not get bytes from image", e);
        }
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    private ImageMetaData createImageMetadata(MultipartFile multipartImageFile, Image image) throws ImageProcessingException, IOException {
        int streamLength = multipartImageFile.getBytes().length;
        ByteArrayInputStream imageInputArray = new ByteArrayInputStream(multipartImageFile.getBytes());
        ImageMetaData imageMetaData = MetaDataUtils.getImageMetaData(imageInputArray, streamLength);
        imageMetaData.setImage(image);

        return imageMetaData;
    }

    private Image createImage(ImageUploadDto imageUploadDto) {
        String imageName = FileUtils.getFileName(imageUploadDto.getImage(), imageUploadDto.getImageName());
        Image image = new Image();
        image.setName(imageUploadDto.getImageName());
        image.setFileName(imageName);
        image.setDescription(imageUploadDto.getDescription());

        return image;
    }

}
