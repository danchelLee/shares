package com.dc.sharesdemo.dao;

import com.dc.sharesdemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findUserEntitiesById(int id);

    UserEntity findUserEntityByUsernameAndPwd(String username,String pwd);

}
