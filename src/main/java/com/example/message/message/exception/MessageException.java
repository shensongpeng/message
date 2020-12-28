package com.example.message.message.exception;/*
 *
 * @ClassName MessageException
 * @Author shensongpeng
 * @Date 2020/12/16 :16:23
 * @Version 1.0
 * */

import com.example.message.message.enums.ResultEnum;

public class MessageException extends RuntimeException{
    private Integer code;

    public MessageException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = code;
    }
    public MessageException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public MessageException() {
        super();
    }


}
