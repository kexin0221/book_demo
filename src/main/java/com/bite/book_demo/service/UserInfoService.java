package com.bite.book_demo.service;

import com.bite.book_demo.model.UserInfo;
import com.bite.book_demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo queryUserInfoByName(String name) {
        return userInfoMapper.queryUserByName(name);
    }
}
