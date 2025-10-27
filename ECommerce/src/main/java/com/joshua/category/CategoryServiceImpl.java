package com.joshua.category;

import com.joshua.exception.APIException;
import com.joshua.exception.ResourceNotFoundException;
import com.joshua.payload.CategoryDTO;
import com.joshua.payload.CategoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories =  categoryPage.getContent();
        if (categories.isEmpty())
            throw new APIException("No Category created till now");
        List<CategoryDTO> categoryDTOS = categories.stream().map(category->modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryResponse.getTotalElements());
        categoryResponse.setTotalPages(categoryResponse.getTotalPages());
        categoryResponse.setLastPage(categoryResponse.isLastPage());
        return categoryResponse;

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null)
            throw new APIException("Category with the name "+category.getCategoryName()+" already exist");

        categoryRepository.save(category);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    public CategoryDTO deleteCategory(int categoryId) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getCategoryId() == categoryId) {
                categoryRepository.delete(category);
                return modelMapper.map(category, CategoryDTO.class);
            }
        }
        throw new ResourceNotFoundException("Category","CategoryId",categoryId);

    }

    public CategoryDTO updateCategory(CategoryDTO updatecategoryDTO, int categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));
        Category category = modelMapper.map(updatecategoryDTO,Category.class);
        category.setCategoryId(categoryId);

        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
