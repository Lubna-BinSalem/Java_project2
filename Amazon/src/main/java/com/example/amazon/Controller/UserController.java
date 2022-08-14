package com.example.amazon.Controller;

import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.Product;
import com.example.amazon.Model.User;
import com.example.amazon.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class UserController {

private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity getUser(){
        ArrayList<User> userList = userService.getUserList();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponse("New user added!",200));
        }
    @PutMapping("/user/{index}")
    public ResponseEntity updateUser(@RequestBody @Valid User user
            , @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        userService.updateUser(user, index);
        return ResponseEntity.status(200).body(new ApiResponse("User updated!", 200));
    }

    @DeleteMapping("/user/{index}")
    public ResponseEntity deleteUser(@PathVariable int index) {
        userService.deleteUser(index);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted!", 200));
    }
//    @PostMapping(path="/user/addProduct")
//    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
//        }
//        userService.addProduct(product);
//        return ResponseEntity.status(200).body(new ApiResponse("User deleted!", 200));
//    }

    @PostMapping(path="/user/buy")
    public ResponseEntity buyProduct(@RequestParam String userID,@RequestParam String productID,@RequestParam String merchantID){
        int isValid=userService.buyProduct(userID,productID,merchantID);
        if(isValid==5){
            return ResponseEntity.status(200).body(new ApiResponse("User bought the product", 200));
        }else if(isValid==1){
            return ResponseEntity.status(400).body(new ApiResponse("UserID is incorrect", 400));
        }else if(isValid==2){
            return ResponseEntity.status(400).body(new ApiResponse("There is no such merchant stock with the given product and merchant ID's", 400));
        }else if(isValid==3){
            return ResponseEntity.status(400).body(new ApiResponse("The stock is zero", 400));
        }else if(isValid==4){
           return ResponseEntity.status(400).body(new ApiResponse("User balance not sufficient", 400));
        }else{
            return ResponseEntity.status(400).body(new ApiResponse("User list is empty", 400));
        }
    }
}
