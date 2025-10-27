package com.joshua.category;

import com.joshua.exception.APIException;
import com.joshua.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories =  categoryRepository.findAll();
        if (categories.isEmpty())
            throw new APIException("No Category created till now");
        return categories;

    }

    @Override
    public String createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new APIException("Category with the name "+category.getCategoryName()+" already exist");

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
        throw new ResourceNotFoundException("Category","CategoryId",categoryId);

    }

    public String updateCategory(Category updatecategory, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryName(updatecategory.getCategoryName());
        categoryRepository.save(category);
        return "Category Updated Successfully";
    }
}
