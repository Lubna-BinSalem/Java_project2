package com.example.amazon.Controller;

import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.Merchant;
import com.example.amazon.Service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class MerchantController {
    MerchantService merchantService ;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/merchant")
    public ResponseEntity getMerchant(){
        ArrayList<Merchant> merchantList = merchantService.getMerchantList();
        return ResponseEntity.status(200).body(merchantList);
    }

    @PostMapping("/merchant")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        merchantService.addMerchant(merchant);
            return ResponseEntity.status(200).body(new ApiResponse("New merchant added!",200));
        }

    @PutMapping("/merchant/{index}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        merchantService.updateMerchant(merchant,index);
            return ResponseEntity.status(200).body(new ApiResponse("merchant updated!",200));
        }

    @DeleteMapping("/merchant/{index}")
    public ResponseEntity deleteMerchant(@PathVariable int index){

       merchantService.deleteMerchant(index);
            return ResponseEntity.status(200).body(new ApiResponse("merchant deleted!",200));
       }
}
