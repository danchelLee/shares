package com.dc.sharesdemo.controller;

import com.dc.sharesdemo.dao.UserRepository;
import com.dc.sharesdemo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Auther: D&C
 * @Descirption:
 * @Time: 2019-03-04
 */
@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/test")
    public String test(){

        UserEntity user = userRepository.findUserEntitiesById(1);

        return user.getUsername()+"----"+user.getPwd();

    }

}
