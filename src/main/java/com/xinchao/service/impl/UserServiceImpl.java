package com.xinchao.service.impl;

import com.xinchao.entity.User;
import com.xinchao.mapper.UserMapper;
import com.xinchao.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
