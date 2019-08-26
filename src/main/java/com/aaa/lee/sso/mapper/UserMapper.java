package com.aaa.lee.sso.mapper;

import com.aaa.lee.sso.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(User user);
}