package com.example.message.message.service.impl;/*
 *
 * @ClassName UserServiceImpl
 * @Author shensongpeng
 * @Date 2020/12/16 :14:47
 * @Version 1.0
 * */

import com.example.message.message.dataobject.User;
import com.example.message.message.repository.UserRepositorry;
import com.example.message.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    final UserRepositorry userRepositorry;



    @Override
    public Optional<User> findOne(Example<User> example) {
        return userRepositorry.findOne(example);
    }

    @Override
    public Optional<User> findOne(Integer userid) {
        return userRepositorry.findById(userid);
    }

    @Override
    public Optional<User> findOne(User user) {
//        userRepositorry.findby
        return Optional.empty();
    }

    @Override
    public Optional findByUserName(String username) {
        //return userRepositorry.findByUserName(username);
        return userRepositorry.findOne(Example.of(new User(username)));
    }

    @Override
    public User findOne(String username) {
        return userRepositorry.findByUserName(username);
    }
    @Override
    public User create(User user) throws SQLException{

        return userRepositorry.save(user);


    }

    @Autowired
    public UserServiceImpl(UserRepositorry userRepositorry) {
        this.userRepositorry = userRepositorry;
    }
}
