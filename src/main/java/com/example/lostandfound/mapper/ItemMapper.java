package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Item;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ItemMapper {

    @Select("SELECT * FROM item ORDER BY create_time DESC")
    List<Item> findAll();

    @Select("SELECT * FROM item WHERE type = #{type} ORDER BY create_time DESC")
    List<Item> findByType(Integer type);
}