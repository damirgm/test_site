package com.server.demo;

import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HelloService {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
