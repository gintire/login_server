package com.example.gintire.products.model;

import lombok.Data;

@Data
public class ProductVO {
    private int id;
    private String name;
    private String desc;
    private int price;
    private String image_path;

    public ProductVO(int id, String name, String desc, int price, String image_path) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image_path = image_path;
    }
}
