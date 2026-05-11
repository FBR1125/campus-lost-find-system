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
        User existId = userMapper.selectById(user.getId());
        if (existId != null) {
            return Result.error("账号已存在！");
        }
        User existName = userMapper.selectByUsername(user.getUsername());
        if (existName != null) {
            return Result.error("用户名已存在！");
        }
        int i = userMapper.register(user);
        return i > 0 ? Result.success("注册成功", user) : Result.error("注册失败");
    }

    // 登录
    public Result<User> login(Integer id, String password) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error("账号不存在");
        }
        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }
        return Result.success("登录成功", user);
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

    // 头像更新
    public boolean updateAvatar(Integer userId, String avatar) {
        return userMapper.updateAvatar(userId, avatar) > 0;
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
    public Result<Claim> claimItem(Claim claim) {
        int i = userMapper.applyClaim(claim);
        userMapper.updateItemStatus(claim.getItemId());
        return i > 0 ? Result.success("认领成功", claim) : Result.error("认领失败");
    }

    // 我的发布
    public List<Item> getMyItems(Integer userId) {
        return userMapper.myItems(userId);
    }

    // 我的认领
    public List<Claim> getMyClaims(Integer userId) {
        return userMapper.myClaims(userId);
    }

    // 改密码
    public boolean updatePassword(Integer userId, String oldPwd, String newPwd) {
        String dbPwd = userMapper.getPwdById(userId);
        if(!oldPwd.equals(dbPwd)){
            return false;
        }
        return userMapper.updatePwd(userId, newPwd) > 0;
    }

    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    public int countFoundItems(Integer userId) {
        return userMapper.countUserFoundItems(userId);
    }
}