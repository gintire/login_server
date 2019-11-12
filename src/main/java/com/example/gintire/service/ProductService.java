package com.example.gintire.service;

import com.example.gintire.model.ProductVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    public ArrayList getProducts() {
        ArrayList<ProductVO> list = new ArrayList<>();
        ProductVO productVO = new ProductVO(1, "IPhone11", "Apple phone", 1500000, "/images/iphone11");
        ProductVO productVO2 = new ProductVO(2, "IPhoneX", "Apple phone", 1100000, "/images/iphoneX");
        list.add(productVO);
        list.add(productVO2);

        return list;
    }
}
