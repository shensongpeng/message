package com.example.message.message.controller;/*
 *
 * @ClassName MessageController
 * @Author shensongpeng
 * @Date 2020/12/16 :20:43
 * @Version 1.0
 * */

import com.example.message.message.VO.ResultVO;
import com.example.message.message.dataobject.InsAwesome;
import com.example.message.message.dataobject.Message;
import com.example.message.message.dto.MessageDTO;
import com.example.message.message.service.InsAweService;
import com.example.message.message.service.MessageService;
import com.example.message.message.service.UserService;
import com.example.message.message.utils.CookieUtil;
import com.example.message.message.utils.ResultVOUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController {
    final MessageService messageService;
    final UserService userService;
    final InsAweService insAweService;
    //创建留言和回复
    @PostMapping("/create")
    public ResultVO<Message> createtest(
            @RequestParam(name = "pid",defaultValue = "-1") Integer  pid,
            @RequestParam(name = "content") String  content,
            @RequestParam(name = "targetUserId",required = false) Integer  targetUserId,
            @RequestParam(name = "type",defaultValue = "0" ) Integer  type,
            HttpServletRequest request) {
        System.out.println(type);
        //设置参数
        Integer userId = Integer.valueOf(CookieUtil.get(request,"userId").getValue());

        Message message = new Message();
        message.setUserId(userId);
        message.setType(type);
        message.setPid(-1);
//        message.setUserId(userId);
//        message.setUserName(username);
        message.setContent(content);
        if (type ==1 ) {
            message.setPid(pid);
            message.setTargetUserId(targetUserId);
        }

        Message result = messageService.create(message);
        return ResultVOUtil.success(result);
    }


    @GetMapping("/getMessages")
    public ModelAndView test(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "pid", defaultValue = "-1") Integer pid,
            HttpServletRequest httpServletRequest,
            Map<String,Object> map) {
        Integer userId = Integer.valueOf(CookieUtil.get(httpServletRequest,"userId").getValue());
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
        List<Integer> childPid = byPidIn.stream().map(e -> e.getId()).collect(Collectors.toList());

        List<Integer> messageIdList = new ArrayList<>(childPid);
        messageIdList.addAll(pidList);
        List<InsAwesome> list = insAweService.findByMessageIDInAndUserId(messageIdList,userId);
        Set<Integer> collect = list.stream().map(
                item -> item.getMessageId()).collect(Collectors.toSet());
        //将回复转换成DTo并且标记是否点赞
        List<MessageDTO> childMessageDTOS = byPidIn.stream().map(item -> {
            MessageDTO messageDTO = new MessageDTO(item);
            if (collect.contains(messageDTO.getId())) {
                messageDTO.setIsLike(true);
            }else messageDTO.setIsLike(false);
            return messageDTO;
        }).collect(Collectors.toList());

        List<MessageDTO> messageDTOS = messagePage.getContent().stream().map(e -> {
            MessageDTO messageDTO = new MessageDTO(e);
            if (collect.contains(messageDTO.getId())) {messageDTO.setIsLike(true);}
            else messageDTO.setIsLike(false);
            return messageDTO;
        }).collect(Collectors.toList());
        messageDTOS = messageDTOS.stream().map(item -> {
            List<MessageDTO> messageList = childMessageDTOS.stream().filter(i ->
                    i.getPid().equals( item.getId())
            ).collect(Collectors.toList());
            item.setMessageList(messageList);
            return item;
        }).collect(Collectors.toList());

        map.put("page",page);
        map.put("currentPage",page);
        map.put("size",size);
        map.put("messageDTOS",messageDTOS);
        map.put("total",messagePage.getTotalElements());
        map.put("totalPages",messagePage.getTotalPages());

        return new ModelAndView("common/boot",map);

    }
    @PostMapping("/getMessages")
    public ModelAndView postGetMessages(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "pid", defaultValue = "-1") Integer pid,
            HttpServletRequest httpServletRequest,
            Map<String,Object> map) {
        Integer userId = Integer.valueOf(CookieUtil.get(httpServletRequest,"userId").getValue());
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
        List<Integer> childPid = byPidIn.stream().map(e -> e.getId()).collect(Collectors.toList());

        List<Integer> messageIdList = new ArrayList<>(childPid);
        messageIdList.addAll(pidList);
        List<InsAwesome> list = insAweService.findByMessageIDInAndUserId(messageIdList,userId);
        Set<Integer> collect = list.stream().map(
                item -> item.getMessageId()).collect(Collectors.toSet());
        //将回复转换成DTo并且标记是否点赞
        List<MessageDTO> childMessageDTOS = byPidIn.stream().map(item -> {
            MessageDTO messageDTO = new MessageDTO(item);
            if (collect.contains(messageDTO.getId())) {
                messageDTO.setIsLike(true);
            }else messageDTO.setIsLike(false);
            return messageDTO;
        }).collect(Collectors.toList());

        List<MessageDTO> messageDTOS = messagePage.getContent().stream().map(e -> {
            MessageDTO messageDTO = new MessageDTO(e);
            if (collect.contains(messageDTO.getId())) {messageDTO.setIsLike(true);}
            else messageDTO.setIsLike(false);
            return messageDTO;
        }).collect(Collectors.toList());
        messageDTOS = messageDTOS.stream().map(item -> {
            List<MessageDTO> messageList = childMessageDTOS.stream().filter(i ->
                    i.getPid().equals( item.getId())
            ).collect(Collectors.toList());
            item.setMessageList(messageList);
            return item;
        }).collect(Collectors.toList());

        map.put("page",page);
        map.put("currentPage",page);
        map.put("size",size);
        map.put("messageDTOS",messageDTOS);
        map.put("total",messagePage.getTotalElements());
        map.put("totalPages",messagePage.getTotalPages());

        return new ModelAndView("common/boot",map);

    }


    public MessageController(MessageService messageService, UserService userService, InsAweService insAweService) {
        this.messageService = messageService;
        this.userService = userService;
        this.insAweService = insAweService;
    }
}
