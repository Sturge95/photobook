package com.sturgeon.photobook.service;

import java.io.File;
import java.io.IOException;

public interface ImageCompressionService {

    File compressImage(File image, String fileExtension) throws IOException;

}
