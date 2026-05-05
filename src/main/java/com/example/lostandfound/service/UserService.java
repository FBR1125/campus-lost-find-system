package com.example.lostandfound.service;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.entity.Claim;
import com.example.lostandfound.mapper.UserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    // 注册
    public Result register(User user) {
        int i = userMapper.register(user);
        return i>0 ? Result.success("注册成功") : Result.error("注册失败");
    }

    // 登录
    public Result login(String username, String password) {
        User user = userMapper.login(username,password);
        return user!=null ? Result.success(user) : Result.error("账号或密码错误");
    }

    // 找回密码
    public Result findPassword(String username,String phone){
        String pwd = userMapper.findPassword(username,phone);
        return pwd!=null ? Result.success(pwd) : Result.error("信息不匹配");
    }

    // 发布物品
    public Result addItem(Item item){
        int i = userMapper.addItem(item);
        return i>0 ? Result.success("发布成功") : Result.error("发布失败");
    }

    // 搜索
    public Result search(String key){
        return Result.success(userMapper.search(key));
    }

    // 认领申请
    public Result applyClaim(Claim claim){
        int i = userMapper.applyClaim(claim);
        return i>0 ? Result.success("申请成功") : Result.error("申请失败");
    }

    // 我的发布
    public Result myItems(Integer uid){
        return Result.success(userMapper.myItems(uid));
    }

    // 我的认领
    public Result myClaims(Integer uid){
        return Result.success(userMapper.myClaims(uid));
    }
}