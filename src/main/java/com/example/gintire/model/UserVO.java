package com.example.gintire.model;

import lombok.Data;

@Data
public class UserVO {
    private int id;
    private String name;
    private String address;
    private String token;

    public UserVO(int id, String name, String address, String token) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.token = token;
    }
}
