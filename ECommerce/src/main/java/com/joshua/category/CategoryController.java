package com.joshua.category;

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
    public ResponseEntity<String> createCategory(@RequestBody Category category){

        String status = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category Added");

    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable int categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity(status, HttpStatusCode.valueOf(200));
        }catch (ResponseStatusException e){
            return new ResponseEntity(e.getMessage(),e.getStatusCode());
        }
    }
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable int categoryId){
        try{
            String responseMessage = categoryService.updateCategory(category,categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
