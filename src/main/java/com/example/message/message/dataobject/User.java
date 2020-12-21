package com.example.message.message.dataobject;/*
 *
 * @ClassName User
 * @Author shensongpeng
 * @Date 2020/12/16 :11:01
 * @Version 1.0
 * */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String userAvatar;
    private String password;
    private String createTime;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userAvatar, that.userAvatar) &&
                Objects.equals(password, that.password);
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userAvatar, password);
    }
}

