package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.mapper.UserMapper;
import com.example.lostandfound.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    // 新增：注入 UserMapper
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(adminUserService.getAllUser());
    }

    @PostMapping("/delete")
    public Result deleteUser(@RequestParam Integer userId) {
        userMapper.deleteById(userId);
        return Result.success("删除成功");
    }

    @PostMapping("/resetPwd")
    public Result resetPwd(@RequestParam Integer userId) {
        userMapper.resetPassword(userId);
        return Result.success("密码已重置为 123456");
    }
}