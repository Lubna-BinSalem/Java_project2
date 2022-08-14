package com.example.amazon.Controller;

import com.example.amazon.Model.ApiResponse;
import com.example.amazon.Model.MerchantStock;
import com.example.amazon.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("pi/v1/amazon")
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    public MerchantStockController(MerchantStockService merchantStockService) {
        this.merchantStockService = merchantStockService;
    }
    @GetMapping("/merchantStock")
    public ResponseEntity getMerchantStocks(){
        ArrayList<MerchantStock> merchantStockList = merchantStockService.getMerchantStockList();
        return ResponseEntity.status(200).body(merchantStockList);
    }

    @PostMapping("/merchantStock")
    public ResponseEntity addMerchantStocks(@RequestBody @Valid MerchantStock marketStock, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        boolean isValid=merchantStockService.addMerchantStock(marketStock);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("New marketStock added!",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid merchant or product id",400));
    }
    @PutMapping("/merchantStock/{index}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock marketStock
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        boolean isValid=merchantStockService.updateMerchantStock(marketStock,index);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("marketStock updated!",200));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Invalid marketStock id",400));
    }

    @DeleteMapping("/merchantStock/{index}")
    public ResponseEntity deleteMerchantStock(@PathVariable int index){

        boolean isValid=merchantStockService.deleteMerchantStock(index);
        if (isValid){
            return ResponseEntity.status(200).body(new ApiResponse("marketStock deleted!",200));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Invalid marketStock id",400));
    }

    @PostMapping("/merchantStock/add")
    public ResponseEntity addProduct(@RequestParam String productID,@RequestParam String merchantID,@RequestParam int stock){

        int isValid=merchantStockService.addProduct(productID,merchantID,stock);
        if(isValid==1){
            return ResponseEntity.status(200).body(new ApiResponse("stock added!",200));
        }else if(isValid==2) {
            return ResponseEntity.status(200).body(new ApiResponse("stock can't be negative!", 200));
        }else{
            return ResponseEntity.status(200).body(new ApiResponse("The productID or merchantID is incorrect!", 200));
        }
    }

}
