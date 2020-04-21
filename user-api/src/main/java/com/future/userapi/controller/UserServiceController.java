package com.future.userapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
