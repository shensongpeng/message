package com.example.message.message.dataobject;/*
 *
 * @ClassName MessagesEntity
 * @Author shensongpeng
 * @Date 2020/12/16 :14:23
 * @Version 1.0
 * */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer type;
    private Timestamp createTime;
    private String content;
    private Integer userId;
    private String userName;
    private String userAvatar;
    private Integer targetUserId;
    private String targetUserName;
    private String targetUserAvatar;
    private Integer pid;
    private Integer likeNum;
    private Integer replyNum;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(content, that.content) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userAvatar, that.userAvatar) &&
                Objects.equals(targetUserId, that.targetUserId) &&
                Objects.equals(targetUserName, that.targetUserName) &&
                Objects.equals(targetUserAvatar, that.targetUserAvatar) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(likeNum, that.likeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, createTime, content, userId, userName, userAvatar, targetUserId, targetUserName, targetUserAvatar, pid, likeNum);
    }
    public void utilOfTarget(User user) {
        setTargetUserId(user.getId());
        setTargetUserName(user.getUserName());
        setTargetUserAvatar(user.getUserAvatar());
    }
}
