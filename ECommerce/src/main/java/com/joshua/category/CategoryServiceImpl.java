package com.joshua.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
//    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public String createCategory(Category category) {

        categoryRepository.save(category);
        return "Category Added Successfully";
    }

    public String deleteCategory(int categoryId) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getCategoryId() == categoryId) {
                categoryRepository.delete(category);
                return "Category with " + categoryId + " deleted";
            }
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource Not Found");

    }

    public String updateCategory(Category updatecategory, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
        category.setCategoryName(updatecategory.getCategoryName());
        categoryRepository.save(category);
        return "Category Updated Successfully";
    }
}
