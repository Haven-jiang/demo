package com.Haven.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String toLogin(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        try {
            subject.login(token);
            return "redirect:/";
        }catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "pages/examples/login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "pages/examples/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "成功退出登录");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "/pages/examples/login";
    }
}
