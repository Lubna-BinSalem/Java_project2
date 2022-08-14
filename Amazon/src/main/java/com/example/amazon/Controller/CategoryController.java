package com.example.amazon.Controller;

import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.Category;
import com.example.amazon.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity getCategory() {
        ArrayList<Category> categoryList = categoryService.getCategoryList();
        return ResponseEntity.status(200).body(categoryList);
    }

    @PostMapping("/category")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("New Category added!", 200));
    }


    @PutMapping("/category/{index}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        categoryService.updateCategory(category, index);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated!", 200));
    }

    @DeleteMapping("/category/{index}")
    public ResponseEntity deleteCategory(@PathVariable int index) {
        categoryService.deleteCategory(index);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted!", 200));
    }
}
