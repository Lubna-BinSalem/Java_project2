package com.example.amazon.Service;

import com.example.amazon.Model.Merchant;
import com.example.amazon.Model.MerchantStock;
import com.example.amazon.Model.Product;
import com.example.amazon.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserService {
    
    ArrayList<User> userList=new ArrayList<User>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;


    public UserService(MerchantStockService merchantStockService,ProductService productService) {
        this.merchantStockService = merchantStockService;
        this.productService=productService;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void updateUser(User user, int index) {
        userList.set(index, user);
    }

    public void deleteUser(int index) {
        userList.remove(index);
    }

    public int buyProduct(String userID, String productID, String merchantID) {
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStockList();
        ArrayList<Product>products=productService.getProductList();
        int price=0;
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId().equals(userID)){
                for (int j = 0; j < merchantStocks.size(); j++) {
                    if(merchantStocks.get(j).getMerchantID().equals(merchantID)&&merchantStocks.get(j).getProductID().equals(productID)){
                        if(!merchantStocks.get(j).getStock().equals("0")){
                            int balance=Integer.parseInt(userList.get(i).getBalance());
                            for (int k = 0; k < products.size(); k++) {
                                if(products.get(k).getId().equals(productID)){
                                    price=Integer.parseInt(products.get(k).getPrice());
                                }}//found the price
                            if(balance>=price){
                                MerchantStock m=new MerchantStock(merchantStocks.get(j).getId(), merchantStocks.get(j).getProductID(), merchantStocks.get(j).getMerchantID(),Integer.toString(Integer.parseInt(merchantStocks.get(j).getStock())-1));
                                merchantStockService.updateMerchantStock(m,j);
                                int newBalance=balance-price;
                                userList.get(i).setBalance(Integer.toString(newBalance));
                                return 5;// all good
                            }
                            return 4;//not enough balance
                        }
                        return 3;//stock is zero
                    }
                    return 2;//no stock

                }
            }
            return 1; //user id not found
        }
        return 0; //no users
    }

//    public void addProduct(Product product) {
//
//    }
}
