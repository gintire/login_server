package com.example.gintire.controller;

import com.example.gintire.model.ResponseVO;
import com.example.gintire.model.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public ResponseVO<?> getUser() {
        ResponseVO<List<UserVO>> resp = new ResponseVO<List<UserVO>>();

        List<UserVO> list = new ArrayList<>();

        list.add(new UserVO(1, "a", "a", "a"));
        list.add(new UserVO(2, "b", "b", "b"));

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseVO<?> getUser(@PathVariable int id) {
        ResponseVO<UserVO> resp = new ResponseVO<>();

        List<UserVO> list = new ArrayList<>();
        list.add(new UserVO(1, "a","a","A"));
        list.add(new UserVO(2, "b", "b", "b"));

        resp.setResponse(list.get(id));
        return resp;
    }
}
