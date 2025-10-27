package com.joshua.category;

import com.joshua.payload.CategoryDTO;
import com.joshua.payload.CategoryResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(int categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
}
