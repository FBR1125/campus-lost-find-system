package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        return Result.success(userService.register(user));
    }

    // 登录接口
    @GetMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        return Result.success(userService.login(username, password));
    }

    // 密码找回
    @GetMapping("/findPassword")
    public Result<String> findPassword(
            @RequestParam String username,
            @RequestParam String phone
    ) {
        return Result.success(userService.findPassword(username, phone));
    }
}