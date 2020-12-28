package com.example.message.message.service.impl;/*
 *
 * @ClassName MessageServiceImpl
 * @Author shensongpeng
 * @Date 2020/12/16 :14:48
 * @Version 1.0
 * */

import com.example.message.message.dataobject.Message;
import com.example.message.message.dataobject.User;
import com.example.message.message.exception.MessageException;
import com.example.message.message.enums.ResultEnum;
import com.example.message.message.repository.MessageRepository;
import com.example.message.message.service.MessageService;
import com.example.message.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    final UserService userService;
    private MessageRepository messageRepository;



    @Override
    @Transactional
    public Message create(Message message, String userName) {
        //创建留言
        //查询发布留言的人是否存在
        User userExample = new User();
        userExample.setUserName(userName);
        Optional<User> userOptional = userService.findOne(Example.of(userExample));
        if (!userOptional.isPresent()) {
            throw new MessageException(ResultEnum.USER_NOT_EXIT);
        }
        if (message.getPid() !=-1) {
            Optional<Message> optionalReplyMessage = messageRepository.findById(message.getPid());
            if (optionalReplyMessage.isPresent()) {
                Message replyMessage = optionalReplyMessage.get();
                replyMessage.setReplyNum(replyMessage.getReplyNum()+1);
                messageRepository.save(replyMessage);
            } else throw new MessageException(ResultEnum.REPLY_MESSAGE_NOT_EXIT);
        }
        return messageRepository.save(message);

    }

    @Override
    public Page<Message> getMessages(Example<Message> example, Pageable pageable) {
        return messageRepository.findAll(example,pageable);
    }

    @Override
    public Optional<Message> findOne(Example<Message> example) {
        return messageRepository.findOne(example);
    }

    @Override
    public Optional<Message> findOne(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    @Override
    public List<Message> findByPidIn(List<Integer> pids) {
        return messageRepository.findByPidInOrderByCreateTimeDesc(pids);
    }

    @Override
    public Page<Message> getMessages(Pageable pageable) {

        return  messageRepository.findAll(pageable);
    }


    public Message create(Message message) {

//        Optional<User> optionalUser = userService.findByUserName(message.getUserName());
        Optional<User> optionalUser = userService.findOne(message.getUserId());
        if (!optionalUser.isPresent()) {
            throw new MessageException(ResultEnum.USER_NOT_EXIT);
        }

        message.setUserId(optionalUser.get().getId());
        message.setUserName(optionalUser.get().getUserName());
        message.setUserAvatar(optionalUser.get().getUserAvatar());

        if (message.getType() == 1) {
            if (message.getTargetUserId() == null) throw new MessageException(ResultEnum.TARGET_USER_ID_IS_NULL);
            Optional<User> targetUserOptional = userService.findOne(message.getTargetUserId());
            if (!targetUserOptional.isPresent()) throw new MessageException(ResultEnum.USER_NOT_EXIT);
            message.utilOfTarget(targetUserOptional.get());
            Optional<Message> optionalReplyMessage = messageRepository.findById(message.getPid());
            if (optionalReplyMessage.isPresent()) {
                Message replyMessage = optionalReplyMessage.get();
                replyMessage.setReplyNum(replyMessage.getReplyNum()+1);
                messageRepository.save(replyMessage);
            } else throw new MessageException(ResultEnum.REPLY_MESSAGE_NOT_EXIT);
        }
        return messageRepository.save(message);

    }

    @Autowired
    public MessageServiceImpl(UserService userService, MessageRepository messageRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
    }
}
