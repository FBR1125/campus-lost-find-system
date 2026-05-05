package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.entity.Claim;
import com.example.lostandfound.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    // 登录
    @GetMapping("/login")
    public Result login(String username,String password){
        return userService.login(username,password);
    }

    // 找回密码
    @GetMapping("/findPassword")
    public Result findPwd(String username,String phone){
        return userService.findPassword(username,phone);
    }

    // 发布失物
    @PostMapping("/add/lost")
    public Result addLost(@RequestBody Item item){
        item.setType(0);
        return userService.addItem(item);
    }

    // 发布招领
    @PostMapping("/add/found")
    public Result addFound(@RequestBody Item item){
        item.setType(1);
        return userService.addItem(item);
    }

    // 搜索物品
    @GetMapping("/search")
    public Result search(String keyword){
        return userService.search(keyword);
    }

    // 申请认领
    @PostMapping("/claim")
    public Result claim(@RequestBody Claim claim){
        return userService.applyClaim(claim);
    }

    // 个人中心：我的发布
    @GetMapping("/my/items")
    public Result myItems(Integer userId){
        return userService.myItems(userId);
    }

    // 个人中心：我的认领
    @GetMapping("/my/claims")
    public Result myClaims(Integer userId){
        return userService.myClaims(userId);
    }
}