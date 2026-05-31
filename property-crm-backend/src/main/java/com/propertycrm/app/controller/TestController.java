package com.propertycrm.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "JWT Authentication Working";
    }
}
//This controller is created for just getting jwt token for testing purpose