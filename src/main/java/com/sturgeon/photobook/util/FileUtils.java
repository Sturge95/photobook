package com.sturgeon.photobook.util;

import liquibase.util.file.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return file;
    }

    public static String getFileName(MultipartFile image, String desiredName) {
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        StringBuilder filename = new StringBuilder();
        desiredName = desiredName.replaceAll(" ", "");
        if (desiredName.contains(extension)) {
            filename.append(desiredName, 0, desiredName.indexOf(extension));
        } else {
            filename.append(desiredName);
        }
        filename.append("-").append(DateUtils.dateFileNameFormat()).append(".").append(extension);
        return filename.toString();
    }
}
