package com.joshua.category;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    String createCategory(Category category);
    String deleteCategory(int categoryId);
    String updateCategory(Category category,int categoryId);
}
