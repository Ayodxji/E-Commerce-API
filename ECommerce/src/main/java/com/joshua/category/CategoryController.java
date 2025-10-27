package com.joshua.category;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping("/api/public/categories")
    @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories =  categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){

        String status = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category Added");

    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable int categoryId){

            String status = categoryService.deleteCategory(categoryId);

            return ResponseEntity.status(HttpStatus.OK).body(status);
    }
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,@PathVariable int categoryId){
            String responseMessage = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with category id: "+categoryId,HttpStatus.OK);

    }
}
