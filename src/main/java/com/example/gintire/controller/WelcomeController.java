package com.example.gintire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome" ;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam("name") String name, @org.jetbrains.annotations.NotNull Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
