package com.example.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.lostandfound.mapper.UserMapper;
import java.util.Map;
import java.util.HashMap;
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

    @Autowired
    private UserMapper userMapper;

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    // 登录
    @GetMapping("/login")
    public Result login(Integer id,String password){
        return userService.login(id,password);
    }

    // 找回密码
    @GetMapping("/findPassword")
    public Result findPwd(String username,String phone){
        return userService.findPassword(username,phone);
    }

    // 头像更新接口
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestBody Map<String, String> map) {
        Integer userId = Integer.parseInt(map.get("id"));
        String avatar = map.get("avatar");
        boolean ok = userService.updateAvatar(userId, avatar);
        return ok ? Result.success("头像更新成功") : Result.error("头像更新失败");
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
        return userService.claimItem(claim);
    }

    // 个人中心：我的发布
    @GetMapping("/my/items")
    public Result myItems(@RequestParam Integer userId) {
        return Result.success(userService.getMyItems(userId));
    }

    // 个人中心：我的认领
    @GetMapping("/my/claims")
    public Result myClaims(@RequestParam Integer userId) {
        return Result.success(userService.getMyClaims(userId));
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> map){
        Integer userId = Integer.parseInt(map.get("userId"));
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        String rePwd = map.get("rePwd");

        if(oldPwd==null || newPwd==null || rePwd==null){
            return Result.error("不能为空");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("两次新密码不一致");
        }

        boolean res = userService.updatePassword(userId, oldPwd, newPwd);
        if(res){
            return Result.success("密码修改成功，请重新登录");
        }else{
            return Result.error("原密码错误");
        }
    }

    @GetMapping("/stats")
    public Result<Map<String, Object>> getUserStats(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        int foundCount = userService.countFoundItems(userId);
        map.put("foundCount", foundCount);
        return Result.success("获取成功", map);
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody User user) {
        userMapper.updateUserInfo(user.getId(), user.getUsername(), user.getPhone());
        return Result.success("修改成功");
    }

    @PostMapping("/deleteAccount")
    public Result deleteAccount(@RequestBody User user) {
        User dbUser = userMapper.selectById(user.getId());
        if (dbUser == null) {
            return Result.error("用户不存在");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            return Result.error("密码错误");
        }
        userMapper.deleteUserById(user.getId());
        return Result.success("注销成功");
    }

}