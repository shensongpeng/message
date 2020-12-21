package com.example.message.message.service;

import com.example.message.message.dataobject.User;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.data.domain.Example;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    Optional<User> findOne(Example<User> userExample);
    Optional<User> findOne(User user);
    User findOne(String username);
    Optional<User> findOne(Integer userid);
    Optional findByUserName(String username);
    User create(User user) throws SQLException;
}
