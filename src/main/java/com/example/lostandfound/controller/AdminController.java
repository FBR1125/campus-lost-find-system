package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.mapper.UserMapper;
import com.example.lostandfound.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.lostandfound.mapper.ItemMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lostandfound.entity.User;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    // 管理员登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return adminService.login(username, password);
    }

    //管理员统计接口
    @GetMapping("/stats")
    public Result<Map<String, Integer>> getStats() {
        Map<String, Integer> stats = new HashMap<>();

        // 1. 总用户数
        int userCount = userMapper.countUser();
        stats.put("userCount", userCount);

        // 2. 失物记录总数
        int itemCount = itemMapper.countAll();
        stats.put("itemCount", itemCount);

        // 3. 待审核认领数（status=0）
        int pendingCount = userMapper.countPendingClaims();
        stats.put("pendingCount", pendingCount);

        // 4. 已找回物品数（status=1）
        int foundCount = itemMapper.countFoundItems();
        stats.put("foundCount", foundCount);

        return Result.success("获取成功", stats);
    }

    // 浏览器直接访问测试管理员登录
    @GetMapping("/testLogin")
    public Result<User> testLogin(){
        return adminService.login("admin", "admin123");
    }
}