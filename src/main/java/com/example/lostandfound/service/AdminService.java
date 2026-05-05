package com.example.lostandfound.service;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AdminService {

    @Autowired
    private UserMapper userMapper;

    public Result<User> login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 2. 校验密码
        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        // 3. 判断是否是管理员
        if (!"admin".equals(user.getRole())) {
            return Result.error("无管理员权限");
        }

        // 4. 登录成功，把 user 对象一起返回
        return Result.success("管理员登录成功", user);
    }
}