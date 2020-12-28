package com.example.message.message.exception;

import com.example.message.message.enums.ResultEnum;

/**
 * Created by wwd
 */
public class UserAuthorizeException extends RuntimeException {
    private Integer code;

    public UserAuthorizeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
