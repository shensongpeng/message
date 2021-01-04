package com.example.message.message.controller;/*
 *
 * @ClassName AweController
 * @Author shensongpeng
 * @Date 2020/12/28 :15:28
 * @Version 1.0
 * */

import com.example.message.message.VO.ResultVO;
import com.example.message.message.dataobject.InsAwesome;
import com.example.message.message.dataobject.Message;
import com.example.message.message.enums.InsAwesomeEnum;
import com.example.message.message.enums.ResultEnum;
import com.example.message.message.exception.MessageException;
import com.example.message.message.service.InsAweService;
import com.example.message.message.service.MessageService;
import com.example.message.message.utils.CookieUtil;
import com.example.message.message.utils.ResultVOUtil;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

//点赞
@RestController
@RequestMapping("/awe")
public class InsAweController {
    final InsAweService insAweService;
    final MessageService messageService;

    public InsAweController(InsAweService insAweService, MessageService messageService) {
        this.insAweService = insAweService;
        this.messageService = messageService;
    }

    @PostMapping("/createAwe")
    public ResultVO create(@RequestParam("messageId") Integer messageId,
                           HttpServletRequest httpServletRequest) {
        Integer userId = Integer.valueOf(CookieUtil.get(httpServletRequest,"userId").getValue());
        InsAwesome insAwesome = new InsAwesome();
        insAwesome.setMessageId(messageId);
        insAwesome.setUserId(userId);
        Optional<InsAwesome> insAwesomeOptional = insAweService.findOne(Example.of(insAwesome));
        if (insAwesomeOptional.isPresent()) {
            InsAwesome awesome = insAwesomeOptional.get();
            awesome.setType(awesome.getType()%2+1);
            return ResultVOUtil.success(insAweService.save(awesome));
        }
        else {

            Optional<Message> one = messageService.findOne(messageId);
            if (!one.isPresent()) {
                throw new MessageException(ResultEnum.MESSAGE_NOT_EXIT);
            }
            insAwesome.setAwedUserId(one.get().getUserId());
            insAwesome.setType(InsAwesomeEnum.LIKE.getCode());
            return ResultVOUtil.success(insAweService.save(insAwesome));
        }

    }
}
