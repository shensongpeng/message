package com.example.message.message.exception;/*
 *
 * @ClassName MessageException
 * @Author shensongpeng
 * @Date 2020/12/16 :16:23
 * @Version 1.0
 * */

import com.example.message.message.enums.ResultEnum;

public class QueryException extends RuntimeException{
    private Integer code;

    public QueryException(ResultEnum resultEnum) {
        this.code = code;
    }
}
