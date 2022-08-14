package com.example.amazon.Service;


import com.example.amazon.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchantList=new ArrayList<Merchant>();
    public ArrayList<Merchant> getMerchantList() {
        return merchantList;
    }
    public void addMerchant(Merchant merchant) {
                merchantList.add(merchant);
    }

    public void updateMerchant(Merchant merchant, int index) {
        merchantList.set(index,merchant);
    }

    public void deleteMerchant(int index) {
        merchantList.remove(index);
    }
}


