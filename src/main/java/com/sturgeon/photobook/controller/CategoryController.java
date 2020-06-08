package com.sturgeon.photobook.controller;

import com.sturgeon.photobook.bo.Category;
import com.sturgeon.photobook.dto.MessageResponse;
import com.sturgeon.photobook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok(new MessageResponse("New category created"));
    }
}
