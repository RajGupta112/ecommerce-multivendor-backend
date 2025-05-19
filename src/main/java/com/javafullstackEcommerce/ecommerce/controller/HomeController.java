package com.javafullstackEcommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    public String HomeControllerHandler(){
        return "welcome";
    }
}
