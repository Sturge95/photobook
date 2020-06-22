package com.sturgeon.photobook.controller;

import com.sturgeon.photobook.bo.Category;
import com.sturgeon.photobook.dto.CategoryDto;
import com.sturgeon.photobook.dto.MessageResponse;
import com.sturgeon.photobook.mapping.ObjectMapper;
import com.sturgeon.photobook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok(new MessageResponse("New category created"));
    }

    @GetMapping("/load-all")
    public ResponseEntity<?> loadAllCategories() {
        List<CategoryDto> categoryDtos = objectMapper.mapAll(categoryService.loadAllCategories(), CategoryDto.class);
        return ResponseEntity.ok(categoryDtos);
    }


}
