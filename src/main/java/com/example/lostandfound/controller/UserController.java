package com.example.lostandfound.controller;

import java.util.Map;
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
    public Result login(Integer id,String password){
        return userService.login(id,password);
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
        // 从localStorage存的userId拿
        Integer userId = Integer.parseInt(map.get("userId"));
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        String rePwd = map.get("rePwd");

        // 非空
        if(oldPwd==null || newPwd==null || rePwd==null){
            return Result.error("不能为空");
        }
        // 两次新密码一致
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
}