package com.sturgeon.photobook.service;

import com.sturgeon.photobook.bo.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> loadAllCategories();

    Optional<Category> getCategory(String name);

    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);
}
