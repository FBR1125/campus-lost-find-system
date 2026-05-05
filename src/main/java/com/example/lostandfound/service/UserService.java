package com.example.lostandfound.service;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.Claim;
import com.example.lostandfound.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 注册
    public Result<User> register(User user) {
        int i = userMapper.register(user);
        return i > 0 ? Result.success("注册成功", user) : Result.error("注册失败");
    }

    // 登录
    public Result<User> login(String username, String password) {
        User user = userMapper.login(username, password);
        if (user != null) {
            return Result.success("登录成功", user);
        } else {
            return Result.error("账号或密码错误");
        }
    }

    // 找回密码
    public Result<String> findPassword(String username, String phone) {
        String password = userMapper.findPassword(username, phone);
        if (password != null) {
            return Result.success("查询成功", password);
        } else {
            return Result.error("用户不存在");
        }
    }

    // 发布物品
    public Result<Item> addItem(Item item) {
        int i = userMapper.addItem(item);
        return i > 0 ? Result.success("发布成功", item) : Result.error("发布失败");
    }

    // 搜索物品
    public Result<List<Item>> search(String key) {
        List<Item> list = userMapper.search(key);
        return Result.success("查询成功", list);
    }

    // 认领申请
    public Result<Claim> applyClaim(Claim claim) {
        int i = userMapper.applyClaim(claim);
        return i > 0 ? Result.success("申请成功", claim) : Result.error("申请失败");
    }

    // 我的发布
    public Result<List<Item>> myItems(Integer uid) {
        List<Item> list = userMapper.myItems(uid);
        return Result.success("查询成功", list);
    }

    // 我的认领
    public Result<List<Claim>> myClaims(Integer uid) {
        List<Claim> list = userMapper.myClaims(uid);
        return Result.success("查询成功", list);
    }
}