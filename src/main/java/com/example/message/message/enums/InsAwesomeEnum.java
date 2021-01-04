package com.example.message.message.enums;

import lombok.Getter;

@Getter
public enum InsAwesomeEnum {
    LIKE(1,"赞"),
    UNLIKE(2,"没赞")
    ;

    private Integer code;

    private String message;

    InsAwesomeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
