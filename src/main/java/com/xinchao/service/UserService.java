package com.xinchao.service;

import com.xinchao.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有数据
     * @return
     */
    List<User> selectAll();
}
