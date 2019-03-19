package com.dc.sharesdemo.controller;

import com.dc.sharesdemo.dao.UserRepository;
import com.dc.sharesdemo.entity.UserEntity;
import com.dc.sharesdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: D&C
 * @Descirption:
 * @Time: 2019-03-04
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @PostMapping("/test")
    public String test(){

        boolean success = userService.login("admin","admin");

        return "Login "+success+"!";

    }

}
