package com.example.amazon.Controller;

import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.Product;
import com.example.amazon.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("pi/v1/amazon")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity getProducts(){
        ArrayList<Product> productList = productService.getProductList();
        return ResponseEntity.status(200).body(productList);
    }

    @PostMapping("/product")
    public ResponseEntity addProducts(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        boolean isValid=productService.addProduct(product);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("New Product added!",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid category id",400));
    }
    @PutMapping("/product/{index}")
    public ResponseEntity updateProduct(@RequestBody @Valid Product product
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        boolean isValid=productService.updateProduct(product,index);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("Product updated!",200));
        }else
        return ResponseEntity.status(400).body(new ApiResponse("Invalid product id",400));
    }

    @DeleteMapping("/product/{index}")
    public ResponseEntity deleteProduct(@PathVariable int index){

        boolean isValid=productService.deleteProduct(index);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted!",200));
        }else
        return ResponseEntity.status(400).body(new ApiResponse("Invalid Product id",400));
    }
}
