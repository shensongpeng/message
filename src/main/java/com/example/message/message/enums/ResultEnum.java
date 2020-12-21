package com.example.message.message.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    USER_NOT_EXIT(1,"用户不存在"),
    USER_EXIT(2,"用户已存在"),
    USER_CREATE_FAIL(3,"用户创建失败"),
    TARGET_USER_ID_IS_NULL(4,"回复用户id为空"),
    REPLY_MESSAGE_NOT_EXIT(5,"回复的留言不存在"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
