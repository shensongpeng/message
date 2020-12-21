package com.example.message.message.service.impl;

import com.example.message.message.dataobject.User;
import com.example.message.message.repository.UserRepositorry;
import com.example.message.message.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositorry userRepositorry;

    @Test
    void findOne() {
    }

    @Test
    void testFindOne() {
    }

    @Test
    void testFindOne1() {
    }

    @Test
    void findByUserName() {
//        User user = userService.findOne("song");
        System.out.println(userService.findByUserName("song"));
        //System.out.println(user);
    }

    @Test
    void create() {
    }
}