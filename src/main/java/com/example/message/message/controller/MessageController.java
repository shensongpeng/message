package com.example.message.message.controller;/*
 *
 * @ClassName MessageController
 * @Author shensongpeng
 * @Date 2020/12/16 :20:43
 * @Version 1.0
 * */

import com.example.message.message.VO.ResultVO;
import com.example.message.message.dataobject.Message;
import com.example.message.message.service.MessageService;
import com.example.message.message.service.UserService;
import com.example.message.message.utils.ResultVOUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController {
    final MessageService messageService;
    final UserService userService;
    //创建留言和回复
    @PostMapping("/create")
    public ResultVO<Message> create(Message message) {
        Message result = messageService.create(message, message.getUserName());
        return ResultVOUtil.success(result);
    }

    //创建留言和回复
    @PostMapping("/create1")
    public ResultVO<Message> createtest(@RequestParam Map<String ,Object> params) {
        System.out.println(params);
        Message message = new Message();

       // Message result = messageService.create(message, message.getUserName());
        return ResultVOUtil.success(params);
    }
    //创建留言和回复
    @PostMapping("/create2")
    public ResultVO<Message> createtest1(
            @RequestParam(name = "username") String  username,
            @RequestParam(name = "userId") Integer  userId,
            @RequestParam(name = "pid",defaultValue = "-1") Integer  pid,
            @RequestParam(name = "content") String  content,
            @RequestParam(name = "targetUserId",required = false) Integer  targetUserId,
            @RequestParam(name = "type",defaultValue = "0" ) Integer  type) {
        System.out.println(type);
        //设置参数
        Message message = new Message();
        message.setType(type);
        message.setPid(pid);
        message.setUserId(userId);
        message.setUserName(username);
        message.setContent(content);
        if (type ==1 ) message.setTargetUserId(targetUserId);

        Message result = messageService.create(message);
        return ResultVOUtil.success(result);
    }
    @PostMapping("/getMessages")
    public ResultVO<List> getMessages(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(name = "pid", defaultValue = "-1") Integer pid,
            Map<String,Object> map) {
        PageRequest request = PageRequest.of(page-1,size);
        Message query = new Message();
        query.setPid(pid);
        Example<Message> example = Example.of(query);
        Page<Message> messagePage = messageService.getMessages(example,request);
        map.put("page",page);
        map.put("size",size);
        map.put("messageList",messagePage.getContent());
        map.put("total",messagePage.getTotalElements());
        List<Integer> pidList = messagePage.getContent().stream().map(e -> e.getPid()).collect(Collectors.toList());
        List<Message> byPidIn = messageService.findByPidIn(pidList);
        return ResultVOUtil.success(map);
    }



    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
}
