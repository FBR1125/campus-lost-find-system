package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Item;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ItemMapper {

    // 首页查询 → 只显示已审核通过的物品
    @Select("SELECT * FROM item WHERE check_status = 1 ORDER BY create_time DESC")
    List<Item> findAll();

    @Select("SELECT * FROM item WHERE type = #{type} ORDER BY create_time DESC")
    List<Item> findByType(Integer type);

    // 统计所有物品总数
    @Select("SELECT COUNT(*) FROM item")
    int countAll();

    // 统计已找回物品数
    @Select("SELECT COUNT(*) FROM item WHERE status = 1")
    int countFoundItems();

    // 最新3条捡到（只查已审核）
    @Select("SELECT * FROM item WHERE type=1 AND check_status=1 ORDER BY create_time DESC LIMIT 3")
    List<Item> findLatestFound();

    // 最新3条丢失（只查已审核）
    @Select("SELECT * FROM item WHERE type=0 AND check_status=1 ORDER BY create_time DESC LIMIT 3")
    List<Item> findLatestLost();

    // 发布物品
    @Insert("INSERT INTO item(user_id, name, place, description, contact, type, status, check_status, create_time) " +
            "VALUES(#{userId}, #{name}, #{place}, #{description}, #{contact}, #{type}, #{status}, 0, NOW())")
    int addItem(Item item);

    @Update("UPDATE item SET check_status = #{status} WHERE id = #{id}")
    int updateCheckStatus(@Param("id") Integer id, @Param("status") Integer status);

    // 获取所有待审核的发布（check_status=0）
    @Select("SELECT * FROM item WHERE check_status = 0 ORDER BY create_time DESC")
    List<Item> findPendingItems();

    // 统计已审核通过的物品数（check_status=1）
    @Select("SELECT COUNT(*) FROM item WHERE check_status = 1")
    int countApprovedItems();

    // 根据ID查询物品
    @Select("SELECT * FROM item WHERE id = #{id}")
    Item findById(Integer id);

    // 认领物品：将 status 设为 1（已认领）
    @Update("UPDATE item SET status = 1 WHERE id = #{id}")
    int updateStatusToClaimed(@Param("id") Integer id);

    // 加在你的 ItemMapper 接口里
    @Update("UPDATE item SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
}