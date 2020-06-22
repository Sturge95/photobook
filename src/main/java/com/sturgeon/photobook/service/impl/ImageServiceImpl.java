package com.sturgeon.photobook.service.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.sturgeon.photobook.bo.Image;
import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.bo.ImageUploadDto;
import com.sturgeon.photobook.repository.ImageRepository;
import com.sturgeon.photobook.service.ImageMetaDataService;
import com.sturgeon.photobook.service.ImageService;
import com.sturgeon.photobook.util.MetaDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public void uploadImage(ImageUploadDto imageUploadDto) {
        try {
            MultipartFile imageFile = imageUploadDto.getImage();
            int streamLength = imageFile.getBytes().length;

            ByteArrayInputStream imageInputArray = new ByteArrayInputStream(imageFile.getBytes());

            Image image = createImage(imageUploadDto);
            Map<String, String> imageData = getImageMetaData(imageInputArray, streamLength);
            ImageMetaData imageMetaData = MetaDataUtils.createMeteDataObject(imageData);
            imageMetaData.setImage(image);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(imageInputArray.readAllBytes().length);

            amazonClientService.uploadImage(imageFile, image.getFileName(), unCompressedBucket);
            imageMetaDataService.saveImageMetadata(imageMetaData);
            //save images and metadata here
        } catch (IOException | ImageProcessingException e) {
            logger.error("could not get bytes from image", e);
        }
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    private Map<String, String> getImageMetaData(ByteArrayInputStream image, int streamLength) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(image, streamLength);
        Map<String, String> imageMetadata = new HashMap<>();
        metadata.getDirectories().forEach(directory -> {
            String directoryName = directory.getName();
            if (!directoryName.equals("xmp") && !directoryName.equals("Interoperability") && !directoryName.equals("GPS") && !directoryName.equals("Exif Thumbnail"))
                directory.getTags().forEach(tag -> {
                    imageMetadata.put(tag.getTagName(), tag.getDescription());
                });
        });
        return imageMetadata;
    }

    private Image createImage(ImageUploadDto imageUploadDto) {
        String imageName = MetaDataUtils.getFileName(imageUploadDto.getImage(), imageUploadDto.getImageName());
        Image image = new Image();
        image.setName(imageUploadDto.getImageName());
        image.setFileName(imageName);
        image.setDescription(imageUploadDto.getDescription());

        return image;
    }


}
