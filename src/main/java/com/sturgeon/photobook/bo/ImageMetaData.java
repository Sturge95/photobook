package com.sturgeon.photobook.bo;

import java.util.Date;

public class ImageMetaData {
    private String flash;
    private String digitalZoom;
    private String photoMode;
    private String focalLength;
    private Date dateTime;
    private String shutterSpeed;
    private String lens;
    private String fileExtension;
    private String cameraMake;
    private String orientation;
    private String height;
    private String width;
    private String cameraModel;
    private String aperture;
    private String compression;
    private String iso;
    private String baseIso;

    public ImageMetaData() {

    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public void setDigitalZoom(String digitalZoom) {
        this.digitalZoom = digitalZoom;
    }

    public void setPhotoMode(String photoMode) {
        this.photoMode = photoMode;
    }

    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setBaseIso(String baseIso) {
        this.baseIso = baseIso;
    }

    public String getFlash() {
        return flash;
    }

    public String getDigitalZoom() {
        return digitalZoom;
    }

    public String getPhotoMode() {
        return photoMode;
    }

    public String getFocalLength() {
        return focalLength;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public String getLens() {
        return lens;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public String getCompression() {
        return compression;
    }

    public String getIso() {
        return iso;
    }

    public String getBaseIso() {
        return baseIso;
    }
}
