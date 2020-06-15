package com.sturgeon.photobook.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.bo.ImageUploadDto;
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

    @Autowired
    private AmazonS3 s3Client;
    @Value("${s3.buckets.compressed}")
    private String compressedBucket;
    @Value("${s3.buckets.uncompressed}")
    private String unCompressedBucket;

    @Override
    public void uploadImage(MultipartFile multipartFile, ImageUploadDto imageUploadDto) {
        try {
            byte[] bytes = multipartFile.getBytes();
            ByteArrayInputStream image = new ByteArrayInputStream(bytes);
            int streamLength = bytes.length;
            Map<String, String> imageData = getImageMetaData(image, streamLength);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(streamLength);

            s3Client.putObject(unCompressedBucket, "fileName", image, objectMetadata);
            ImageMetaData imageMetaData = MetaDataUtils.createMeteDataObject(imageData);
        } catch (IOException | ImageProcessingException e) {
            logger.error("could not get bytes from image", e);
        }
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


}
