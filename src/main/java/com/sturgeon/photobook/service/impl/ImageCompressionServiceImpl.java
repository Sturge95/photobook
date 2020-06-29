package com.sturgeon.photobook.service.impl;

import com.sturgeon.photobook.service.ImageCompressionService;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageCompressionServiceImpl implements ImageCompressionService {

    @Override
    public File compressImage(File image, String imageExtension) throws IOException {
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        //TODO Make this configurable
        jpgWriteParam.setCompressionQuality(0.6f);

        File imageFile = new File("newTempImage.jpg");
        InputStream is = new FileInputStream(image);
        BufferedImage bImage = ImageIO.read(is);
        FileImageOutputStream outputStream = new FileImageOutputStream(imageFile);
        jpgWriter.setOutput(outputStream);
        IIOImage outputImage = new IIOImage(bImage, null, null);
        jpgWriter.write(null, outputImage, jpgWriteParam);
        jpgWriter.dispose();
        outputStream.close();

        return imageFile;
    }

}
