package com.example.amazon.Service;
import com.example.amazon.Model.Category;
import com.example.amazon.Model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {
    private ArrayList<Product> productList=new ArrayList<Product>();
    private final CategoryService categoryService;

    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }
    public boolean addProduct(Product product) {
        ArrayList<Category> categories=categoryService.getCategoryList();

        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(product.getCategoryID())){
                productList.add(product);
                return true;
            }
        }

        return false;
    }
    public boolean updateProduct(Product product, int index) {

        if(index>=productList.size()){
            return false;
        }
        productList.set(index,product);
        return true;
    }

    public boolean deleteProduct(int index) {
        if(index>=productList.size()){
            return false;
        }
        productList.remove(index);
        return true;
    }
    }



