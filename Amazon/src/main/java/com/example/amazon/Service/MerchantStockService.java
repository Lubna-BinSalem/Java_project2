package com.example.amazon.Service;

import com.example.amazon.Model.Category;
import com.example.amazon.Model.Merchant;
import com.example.amazon.Model.MerchantStock;
import com.example.amazon.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStockList=new ArrayList<MerchantStock>();
    private final MerchantService merchantService;
    private final ProductService productService;

    public MerchantStockService(MerchantService merchantService, ProductService productService) {
        this.merchantService = merchantService;
        this.productService = productService;
    }

    public ArrayList<MerchantStock> getMerchantStockList() {
        return merchantStockList;
    }
    public boolean addMerchantStock(MerchantStock merchantStock) {
        ArrayList<Merchant> merchants = merchantService.getMerchantList();
        ArrayList<Product> products = productService.getProductList();

        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() .equals( merchantStock.getMerchantID())) {
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getId().equals(merchantStock.getProductID()))
                        merchantStockList.add(merchantStock);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean updateMerchantStock(MerchantStock merchantStock, int index) {
        if(index>=merchantStockList.size()){
            return false;
        }
        merchantStockList.set(index,merchantStock);
        return true;
    }
    public boolean deleteMerchantStock(int index) {
        if(index>=merchantStockList.size()){
            return false;
        }
        merchantStockList.remove(index);
        return true;
    }

    public int addProduct(String productID, String merchantID, int stock) {
        if(stock<0){
            return 2;
        }
        for (int i = 0; i < merchantStockList.size(); i++) {
            if(merchantStockList.get(i).getProductID().equals(productID)&&merchantStockList.get(i).getMerchantID().equals(merchantID)){
                merchantStockList.get(i).setStock(Integer.toString(Integer.parseInt(merchantStockList.get(i).getStock())+stock));
                return 1;//good
            }
        }
        return 3;//cant find stock
    }
}



