package com.example.demo.controller;

import com.example.demo.service.CobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CobaController {

    @Autowired
    private CobaService cobaService;

    @GetMapping("/home")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello/text")
    public String getText() {
        return cobaService.getText();
    }
}
