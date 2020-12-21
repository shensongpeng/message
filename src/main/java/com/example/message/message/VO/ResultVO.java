package com.example.message.message.VO;/*
 *
 * @ClassName ResultVO
 * @Author shensongpeng
 * @Date 2020/12/16 :20:48
 * @Version 1.0
 * */

import lombok.Data;

@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
