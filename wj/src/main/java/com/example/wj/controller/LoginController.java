package com.example.wj.controller;

import com.example.wj.common.Result;
import com.example.wj.entity.User;
import com.example.wj.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser, HttpSession session){
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        username = HtmlUtils.htmlEscape(username);
        User user = loginService.get(username,password);
        if(user!=null){
            logger.info("login success!!!");
            session.setAttribute("user",user);
            return new Result(200);
        }else {
            logger.info("login fail!!!");
            return new Result(400);
        }
    }

    @GetMapping(value = "/test")
    public String test(){
        return "this is test!!!";
    }
}
