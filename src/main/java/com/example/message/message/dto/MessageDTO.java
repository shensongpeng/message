package com.example.message.message.dto;/*
 *
 * @ClassName MessageDTO
 * @Author shensongpeng
 * @Date 2020/12/20 :16:05
 * @Version 1.0
 * */

import com.example.message.message.dataobject.Message;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MessageDTO {
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
    private Boolean isLike;

    private List<MessageDTO> messageList;

    public MessageDTO() {
    }

    public MessageDTO(Message message){
        BeanUtils.copyProperties(message,this);
    }
}
