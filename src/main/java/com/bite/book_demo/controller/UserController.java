package com.bite.book_demo.controller;

import com.bite.book_demo.constant.Constants;
import com.bite.book_demo.model.UserInfo;
import com.bite.book_demo.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public boolean login(String name, String password, HttpSession session) {
        log.info("用户登录, name: {}", name);
        if (!StringUtils.hasLength(name) || !StringUtils.hasLength(password)) {
            return false;
        }
//        if ("admin".equals(name) && "admin".equals(password)) {
//            session.setAttribute("userName", name);
//            return true;
//        }

        // 根据用户名，获取用户信息
        UserInfo userInfo = userInfoService.queryUserInfoByName(name);
        if (userInfo == null) {
            return false;
        }
        if (password.equals(userInfo.getPassword())) {
            session.setAttribute(Constants.SESSION_USER_KEY, userInfo);
            return true;
        }

        return false;
    }
}