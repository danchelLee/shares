package com.dc.sharesdemo.service;

/**
 * @author Dc
 */
public interface UserService {

    /**
     * 登录
     * @param username
     * @param pwd
     * @return
     */
    boolean login(String username,String pwd);

}
