package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 首页展示所有物品
    @GetMapping("/home")
    public Result<List<Item>> home() {
        return Result.success("获取成功", itemService.getHomeList());
    }

    // 分类导航
    @GetMapping("/type")
    public Result<List<Item>> getByType(@RequestParam Integer type) {
        return Result.success("获取成功", itemService.getListByType(type));
    }
}