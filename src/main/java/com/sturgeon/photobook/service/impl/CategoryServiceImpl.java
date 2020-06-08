package com.sturgeon.photobook.service.impl;

import com.sturgeon.photobook.bo.Category;
import com.sturgeon.photobook.repository.CategoryRepository;
import com.sturgeon.photobook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> loadAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
