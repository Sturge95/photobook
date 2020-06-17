package com.sturgeon.photobook.controller;

import com.sturgeon.photobook.bo.ImageUploadDto;
import com.sturgeon.photobook.dto.MessageResponse;
import com.sturgeon.photobook.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@ModelAttribute ImageUploadDto imageUploadDto) throws IOException {
        imageService.uploadImage(imageUploadDto);
        return ResponseEntity.ok(new MessageResponse("New category created"));
    }

}
