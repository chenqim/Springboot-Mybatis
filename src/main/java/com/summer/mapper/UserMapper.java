package com.summer.mapper;

import com.summer.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {
    User getUser();

    List<User> selectAllUser();
}
