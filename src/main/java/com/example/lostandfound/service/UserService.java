package com.example.lostandfound.service;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 注册
    public String register(User user) {
        // 1. 校验用户名是否已存在
        User existUser = userMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            return "用户名已存在";
        }
        // 2. 插入用户
        int result = userMapper.insertUser(user);
        return result > 0 ? "注册成功" : "注册失败";
    }

    // 登录
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return "用户不存在";
        }
        if (!user.getPassword().equals(password)) {
            return "密码错误";
        }
        return "登录成功";
    }

    // 密码找回
    public String findPassword(String username, String phone) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return "用户不存在";
        }
        if (!user.getPhone().equals(phone)) {
            return "手机号不正确";
        }
        return "您的密码是：" + user.getPassword();
    }
}