package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    // 首页展示物品
    @GetMapping("/home")
    public Result<List<Item>> home() {
        return Result.success("获取成功", itemService.getHomeList());
    }

    // 分类导航
    @GetMapping("/type")
    public Result<List<Item>> getByType(@RequestParam Integer type) {
        return Result.success("获取成功", itemService.getListByType(type));
    }

    // 发布物品
    @PostMapping("/add")
    public Result<?> addItem(@RequestBody Item item) {
        // 查询用户角色
        User user = userService.getById(item.getUserId());

        // 如果是管理员，直接禁止发布
        if (user != null && "admin".equals(user.getRole())) {
            return Result.error("管理员无法发布物品");
        }

        int i = itemService.addItem(item);
        if (i > 0) {
            return Result.success("发布成功");
        } else {
            return Result.error("发布失败");
        }
    }

    // 最新3条捡到的（只显示已审核）
    @GetMapping("/latestFound")
    public Result<List<Item>> latestFound() {
        return Result.success("获取成功", itemService.getLatestFound());
    }

    // 最新3条丢失的（只显示已审核）
    @GetMapping("/latestLost")
    public Result<List<Item>> latestLost() {
        return Result.success("获取成功", itemService.getLatestLost());
    }

    // 管理员审核接口
    @PostMapping("/check")
    public Result<?> check(@RequestParam Integer id, @RequestParam Integer status) {
        int i = itemService.updateCheckStatus(id, status);
        if (i > 0) {
            return Result.success("审核成功");
        } else {
            return Result.error("审核失败");
        }
    }

    // 获取待审核列表
    @GetMapping("/pending")
    public Result<List<Item>> pending() {
        return Result.success("获取成功", itemService.getPendingItems());
    }
}