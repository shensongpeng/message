package com.example.message.message.controller;/*
 *
 * @ClassName MessageController
 * @Author shensongpeng
 * @Date 2020/12/16 :20:43
 * @Version 1.0
 * */

import com.example.message.message.VO.ResultVO;
import com.example.message.message.dataobject.Message;
import com.example.message.message.dto.MessageDTO;
import com.example.message.message.service.MessageService;
import com.example.message.message.service.UserService;
import com.example.message.message.utils.ResultVOUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/message")
public class MessageController {
    final MessageService messageService;
    final UserService userService;
//    //创建留言和回复
//    @PostMapping("/create")
//    public ResultVO<Message> create(Message message) {
//        Message result = messageService.create(message, message.getUserName());
//        return ResultVOUtil.success(result);
//    }
//
//    //创建留言和回复
//    @PostMapping("/create1")
//    public ResultVO<Message> createtest(@RequestParam Map<String ,Object> params) {
//        System.out.println(params);
//        Message message = new Message();
//
//       // Message result = messageService.create(message, message.getUserName());
//        return ResultVOUtil.success(params);
//    }
    //创建留言和回复
    @PostMapping("/create")
    public ResultVO<Message> createtest(
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
        message.setPid(-1);
        message.setUserId(userId);
        message.setUserName(username);
        message.setContent(content);
        if (type ==1 ) {
            message.setPid(pid);
            message.setTargetUserId(targetUserId);
        }

        Message result = messageService.create(message);
        return ResultVOUtil.success(result);
    }
//    @PostMapping("/getMessages")
//    public ResultVO<List<MessageDTO>> getMessages(
//            @RequestParam("page") Integer page,
//            @RequestParam("size") Integer size,
//            @RequestParam(name = "pid", defaultValue = "-1") Integer pid,
//            Map<String,Object> map) {
//        //按照发布时间降序排序
//        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
//        PageRequest request = PageRequest.of(page-1,size,sort);
//        Message query = new Message();
//        query.setPid(pid);
//        Example<Message> example = Example.of(query);
//        Page<Message> messagePage = messageService.getMessages(example,request);
//
//
//
//        //将留言的id筛选出来构建一个id的集合，然后查询这些留言的回复保存到byPidIn
//        List<Integer> pidList = messagePage.getContent().stream().map(e -> e.getId()).collect(Collectors.toList());
//        List<Message> byPidIn = messageService.findByPidIn(pidList);
//        //
//        List<MessageDTO> messageDTOS = messagePage.getContent().stream().map(e -> new MessageDTO(e)).collect(Collectors.toList());
//        messageDTOS = messageDTOS.stream().map(item -> {
//            List<Message> messageList = byPidIn.stream().filter(i ->
//                    i.getPid().equals( item.getId())
//            ).collect(Collectors.toList());
//            item.setMessageList(messageList);
//            return item;
//        }).collect(Collectors.toList());
//
//        map.put("page",page);
//        map.put("size",size);
//        map.put("messageDTOS",messageDTOS);
//        map.put("total",messagePage.getTotalElements());
//
//        return ResultVOUtil.success(map);
//    }
    @GetMapping("/getMessages")
    public ModelAndView test(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "pid", defaultValue = "-1") Integer pid,
            Map<String,Object> map) {
        //按照发布时间降序排序
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        PageRequest request = PageRequest.of(page-1,size,sort);
        Message query = new Message();
        query.setPid(pid);
        Example<Message> example = Example.of(query);
        Page<Message> messagePage = messageService.getMessages(example,request);



        //将留言的id筛选出来构建一个id的集合，然后查询这些留言的回复保存到byPidIn
        List<Integer> pidList = messagePage.getContent().stream().map(e -> e.getId()).collect(Collectors.toList());
        List<Message> byPidIn = messageService.findByPidIn(pidList);
        //
        List<MessageDTO> messageDTOS = messagePage.getContent().stream().map(e -> new MessageDTO(e)).collect(Collectors.toList());
        messageDTOS = messageDTOS.stream().map(item -> {
            List<Message> messageList = byPidIn.stream().filter(i ->
                    i.getPid().equals( item.getId())
            ).collect(Collectors.toList());
            item.setMessageList(messageList);
            return item;
        }).collect(Collectors.toList());

        map.put("page",page);
        map.put("size",size);
        map.put("messageDTOS",messageDTOS);
        map.put("total",messagePage.getTotalElements());

        return new ModelAndView("common/boot",map);

    }




    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
}
