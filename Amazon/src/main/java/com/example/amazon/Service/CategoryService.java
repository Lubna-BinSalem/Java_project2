package com.example.amazon.Service;
import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categoryList=new ArrayList<Category>();
    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }
    public void addCategory(Category category) {
                categoryList.add(category);
    }

    public void updateCategory(Category category, int index) {
        categoryList.set(index,category);
    }

    public void deleteCategory(int index) {
        categoryList.remove(index);
    }
}
