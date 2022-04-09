package com.Haven.controller;

import com.Haven.service.UserService;
import com.Haven.vo.UserVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registration() {
        return "/pages/examples/register";
    }

    @PostMapping("/register")
    public String register(String email, String password, String code) {
        UserVO build = UserVO.builder()
                .username(email)
                .password(password)
                .messageCode(code)
                .build();
        userService.register(build);
        return "pages/examples/login";
    }

    @PostMapping("/send")
    @ResponseBody
    public String sendCode(String email) {
        userService.sendCode(email);
        return JSON.toJSONString(userService);
    }
}
