package com.example.message.message.service;/*
 *
 * @ClassName MessageService
 * @Author shensongpeng
 * @Date 2020/12/16 :14:47
 * @Version 1.0
 * */

import com.example.message.message.dataobject.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    Message create(Message message,String userName);
    Message create(Message message);
    Page<Message> getMessages(Pageable pageable);
    Page<Message> getMessages(Example<Message> example, Pageable pageable);
    List<Message> findByPidIn(List<Integer> pids);
}
