package com.sturgeon.photobook.util;

import com.sturgeon.photobook.bo.ImageMetaData;
import com.sturgeon.photobook.constants.MetaDataConstants;

import java.util.Map;

public class MetaDataUtils {

    public static ImageMetaData createMeteDataObject(Map<String, String> imageData) {
        ImageMetaData imageMetaData = new ImageMetaData();
        imageMetaData.setFlash(getFieldData(imageData, MetaDataConstants.FLASH));
        imageMetaData.setDigitalZoom(getFieldData(imageData, MetaDataConstants.DIGITAL_ZOOM));
        imageMetaData.setPhotoMode(getFieldData(imageData, MetaDataConstants.PHOTO_MODE));
        imageMetaData.setFocalLength(getFieldData(imageData, MetaDataConstants.FOCAL_LENGTH));
        imageMetaData.setDateTime(getFieldData(imageData, MetaDataConstants.DATE_TAKEN));
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

        return imageMetaData;
    }

    private static String getFieldData(Map<String, String> imageData, String metaTag) {
        if (imageData.containsKey(metaTag)) {
            return imageData.get(metaTag);
        }
        return null;
    }
}
