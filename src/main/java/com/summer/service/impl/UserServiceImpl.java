package com.summer.service.impl;

import com.summer.mapper.UserMapper;
import com.summer.model.User;
import com.summer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {

        User user = new User();
        user.setUserId(1);
        user.setUserName("chen");
        user.setPassword("123");
        user.setPhone("15623629758");
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAllUser();
    }
}
