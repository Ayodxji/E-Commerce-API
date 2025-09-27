package com.joshua.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private int categoryId = 1;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(categoryId++);
        categories.add(category);
        return "Category Added Successfully";
    }

    public String deleteCategory(int categoryId) {
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                categories.remove(category);
                return "Category with " + categoryId + " deleted";
            }
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource Not Found");

    }

    public String updateCategory(Category updatecategory, int categoryId) {
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                category.setCategoryName(updatecategory.getCategoryName());
                return "Category with " + categoryId + "updated Successfully";
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
    }
}
