package com.example.gintire.products.controller;

import com.example.gintire.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public Object printJSON() {
        return productService.getProducts();
    }
}
