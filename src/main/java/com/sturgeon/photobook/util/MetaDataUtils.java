package com.sturgeon.photobook.util;

import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.constants.MetaDataConstants;
import liquibase.util.file.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MetaDataUtils {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataUtils.class);

    public static ImageMetaData createMeteDataObject(Map<String, String> imageData) {
        ImageMetaData imageMetaData = new ImageMetaData();
        imageMetaData.setFlash(getFieldData(imageData, MetaDataConstants.FLASH));
        imageMetaData.setDigitalZoom(getFieldData(imageData, MetaDataConstants.DIGITAL_ZOOM));
        imageMetaData.setPhotoMode(getFieldData(imageData, MetaDataConstants.PHOTO_MODE));
        imageMetaData.setFocalLength(getFieldData(imageData, MetaDataConstants.FOCAL_LENGTH));
        imageMetaData.setShutterSpeed(getFieldData(imageData, MetaDataConstants.SHUTTER_SPEED));
        imageMetaData.setLens(getFieldData(imageData, MetaDataConstants.LENS));
        imageMetaData.setFileExtension(getFieldData(imageData, MetaDataConstants.EXTENSION));
        imageMetaData.setCameraMake(getFieldData(imageData, MetaDataConstants.CAMERA_MAKE));
        imageMetaData.setOrientation(getFieldData(imageData, MetaDataConstants.ORIENTATION));
        imageMetaData.setWidth(getFieldData(imageData, MetaDataConstants.WIDTH));
        imageMetaData.setHeight(getFieldData(imageData, MetaDataConstants.HEIGHT));
        imageMetaData.setCameraModel(getFieldData(imageData, MetaDataConstants.CAMERA_MODEL));
        imageMetaData.setAperture(getFieldData(imageData, MetaDataConstants.APERTURE));
        imageMetaData.setCompression(getFieldData(imageData, MetaDataConstants.COMPRESSION));
        imageMetaData.setBaseIso(getFieldData(imageData, MetaDataConstants.BASE_IDO));
        imageMetaData.setIso(getFieldData(imageData, MetaDataConstants.ISO_SPEED));
        imageMetaData.setDateTime(getFieldDateData(imageData, MetaDataConstants.DATE_TAKEN));

        return imageMetaData;
    }

    private static String getFieldData(Map<String, String> imageData, String metaTag) {
        if (imageData.containsKey(metaTag)) {
            return imageData.get(metaTag);
        }
        return null;
    }

    private static Date getFieldDateData(Map<String, String> imageData, String metaTag) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
        if (imageData.containsKey(metaTag)) {
            String dateTag = imageData.get(metaTag);
            try {
                return simpleDateFormat.parse(dateTag);
            } catch (ParseException e) {
                logger.error("Could not parse date for tag " + metaTag, e);
            }
        }
        return null;
    }

    public static String getFileName(MultipartFile image, String desiredName) {
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        StringBuilder filename = new StringBuilder();
        if (desiredName.contains(extension)) {
            filename.append(desiredName, 0, desiredName.indexOf(extension));
        }
        filename.append(DateUtils.dateFileNameFormat()).append(".").append(extension);
        return filename.toString();
    }
}
