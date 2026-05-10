package com.example.lostandfound.service;

import com.example.lostandfound.entity.Item;
import com.example.lostandfound.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public List<Item> getHomeList() {
        return itemMapper.findAll();
    }

    public List<Item> getListByType(Integer type) {
        return itemMapper.findByType(type);
    }

    // 发布
    public int addItem(Item item) {
        return itemMapper.addItem(item);
    }

    // 最新3条捡到
    public List<Item> getLatestFound() {
        return itemMapper.findLatestFound();
    }

    // 最新3条丢失
    public List<Item> getLatestLost() {
        return itemMapper.findLatestLost();
    }

    public int updateCheckStatus(Integer id, Integer status) {
        return itemMapper.updateCheckStatus(id, status);
    }

    public List<Item> getPendingItems() {
        return itemMapper.findPendingItems();
    }

    // 根据ID查询物品
    public Item getById(Integer id) {
        return itemMapper.findById(id);
    }

    public int claimItem(Integer id) {
        return itemMapper.updateStatusToClaimed(id);
    }
}