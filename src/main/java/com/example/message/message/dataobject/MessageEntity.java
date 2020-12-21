package com.example.message.message.dataobject;/*
 *
 * @ClassName MessagesEntity
 * @Author shensongpeng
 * @Date 2020/12/16 :14:23
 * @Version 1.0
 * */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "testdemo")
public class MessageEntity {
    private int id;
    private Integer type;
    private Long createTime;
    private String content;
    private Integer userId;
    private Integer userIsAdmin;
    private String userName;
    private String userAvatar;
    private Integer targetUserId;
    private Integer targetUserIsAdmin;
    private String targetUserName;
    private String targetUserAvatar;
    private Integer pid;
    private Integer likeNum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "createTime")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userIsAdmin")
    public Integer getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(Integer userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userAvatar")
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Basic
    @Column(name = "targetUserId")
    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    @Basic
    @Column(name = "targetUserIsAdmin")
    public Integer getTargetUserIsAdmin() {
        return targetUserIsAdmin;
    }

    public void setTargetUserIsAdmin(Integer targetUserIsAdmin) {
        this.targetUserIsAdmin = targetUserIsAdmin;
    }

    @Basic
    @Column(name = "targetUserName")
    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    @Basic
    @Column(name = "targetUserAvatar")
    public String getTargetUserAvatar() {
        return targetUserAvatar;
    }

    public void setTargetUserAvatar(String targetUserAvatar) {
        this.targetUserAvatar = targetUserAvatar;
    }

    @Basic
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "likeNum")
    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(content, that.content) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userIsAdmin, that.userIsAdmin) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userAvatar, that.userAvatar) &&
                Objects.equals(targetUserId, that.targetUserId) &&
                Objects.equals(targetUserIsAdmin, that.targetUserIsAdmin) &&
                Objects.equals(targetUserName, that.targetUserName) &&
                Objects.equals(targetUserAvatar, that.targetUserAvatar) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(likeNum, that.likeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, createTime, content, userId, userIsAdmin, userName, userAvatar, targetUserId, targetUserIsAdmin, targetUserName, targetUserAvatar, pid, likeNum);
    }
}
