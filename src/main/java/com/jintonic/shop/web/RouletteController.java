package com.jintonic.shop.web;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouletteController {
    private static final Logger logger = LoggerFactory.getLogger(RouletteController.class);

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/roulette")
    public String roulette(){ return "roulette/board"; }

}
