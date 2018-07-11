package com.summer.controller;

import com.summer.model.User;
import com.summer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("sayHello")
    public String sayHello () {
        User user = userService.getUser();
        System.out.println("user" + user);

        List<User> list = userService.getAllUser();
        System.out.println("list" + list);
        return "Hello World!";
    }

}