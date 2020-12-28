package com.example.message.message.repository;/*
 *
 * @ClassName MessageRepository
 * @Author shensongpeng
 * @Date 2020/12/16 :14:42
 * @Version 1.0
 * */

import com.example.message.message.dataobject.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPidInOrderByCreateTimeDesc(List<Integer> pids);
}
