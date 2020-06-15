package com.sturgeon.photobook.controller;

import com.sturgeon.photobook.bo.ImageUploadDto;
import com.sturgeon.photobook.dto.MessageResponse;
import com.sturgeon.photobook.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        imageService.uploadImage(file, new ImageUploadDto());
        return ResponseEntity.ok(new MessageResponse("New category created"));
    }

}
