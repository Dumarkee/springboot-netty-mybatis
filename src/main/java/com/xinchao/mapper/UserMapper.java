package com.xinchao.mapper;

import com.xinchao.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectAll();
}
