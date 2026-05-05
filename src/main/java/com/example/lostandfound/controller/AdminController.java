package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lostandfound.entity.User;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 管理员登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return adminService.login(username, password);
    }
    // 浏览器直接访问测试管理员登录
    @GetMapping("/testLogin")
    public Result<User> testLogin(){
        return adminService.login("admin", "admin123");
    }
}