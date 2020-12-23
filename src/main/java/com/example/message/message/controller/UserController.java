package com.example.message.message.controller;/*
 *
 * @ClassName UserController
 * @Author shensongpeng
 * @Date 2020/12/17 :15:56
 * @Version 1.0
 * */

import com.example.message.message.VO.ResultVO;
import com.example.message.message.dataobject.User;
import com.example.message.message.enums.ResultEnum;
import com.example.message.message.exception.MessageException;
import com.example.message.message.service.UserService;
import com.example.message.message.utils.ResultVOUtil;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    //新建用户
    @PostMapping("/create")
    public ResultVO<User> create(@RequestParam("userName") String  userName,
                                 @RequestParam("userAvatar") String userAvatar,
                                 @RequestParam("password") String password
                                 ) throws SQLException {
        User  user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setUserAvatar(userAvatar);
        Optional optional = userService.findByUserName(userName);
        if (optional.isPresent()) {
            throw new MessageException(ResultEnum.USER_NOT_EXIT);
        }
        try {
            User result = userService.create(user);
            return ResultVOUtil.success(result);
        } catch (SQLException e) {
            return ResultVOUtil.success(ResultEnum.USER_CREATE_FAIL.getMessage());
        }





    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            Map<String,Object> map){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        Optional<User> byUserName = userService.findOne(Example.of(user));
        //未查询到
        if (!byUserName.isPresent()) {

        }
        user = byUserName.get();
        map.put("user",user);

        return new ModelAndView("common/boot",map);
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
