package com.example.message.message.repository;

import com.example.message.message.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorry extends JpaRepository<User,Integer> {
    User findByUserName(String user);
}
