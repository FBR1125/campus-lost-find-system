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
}