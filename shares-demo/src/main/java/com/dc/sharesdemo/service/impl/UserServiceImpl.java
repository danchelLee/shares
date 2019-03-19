package com.dc.sharesdemo.service.impl;

import com.dc.sharesdemo.dao.UserRepository;
import com.dc.sharesdemo.entity.UserEntity;
import com.dc.sharesdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: Dc Lee
 * @Descpription:
 * @Date: 9:14 2019/3/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public boolean login(String username,String pwd) {

        UserEntity entity = userRepository.findUserEntityByUsernameAndPwd(username,pwd);

        if (entity!=null){
            return true;
        }else {
            return false;
        }

    }
}
