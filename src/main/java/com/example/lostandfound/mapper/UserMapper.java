package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.entity.Claim;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface UserMapper {

    User selectByUsername(String username);

    // 注册
    @Insert("INSERT INTO user(username,password,phone) VALUES(#{username},#{password},#{phone})")
    int register(User user);

    // 登录
    @Select("SELECT * FROM user WHERE username=#{username} AND password=#{password}")
    User login(String username, String password);

    // 找回密码
    @Select("SELECT password FROM user WHERE username=#{username} AND phone=#{phone}")
    String findPassword(String username, String phone);

    // 发布物品
    @Insert("INSERT INTO item(user_id,name,description,type,status) VALUES(#{userId},#{name},#{description},#{type},0)")
    int addItem(Item item);

    // 搜索物品
    @Select("SELECT * FROM item WHERE name LIKE CONCAT('%',#{keyword},'%') OR description LIKE CONCAT('%',#{keyword},'%')")
    List<Item> search(String keyword);

    // 申请认领
    @Insert("INSERT INTO claim(item_id,user_id,message,status) VALUES(#{itemId},#{userId},#{message},0)")
    int applyClaim(Claim claim);

    // 个人中心：我的发布
    @Select("SELECT * FROM item WHERE user_id=#{userId}")
    List<Item> myItems(Integer userId);

    // 个人中心：我的认领
    @Select("SELECT * FROM claim WHERE user_id=#{userId}")
    List<Claim> myClaims(Integer userId);

    // 统计总用户数
    @Select("SELECT COUNT(*) FROM user")
    int countUser();

    // 统计待审核认领数
    @Select("SELECT COUNT(*) FROM claim WHERE status = 0")
    int countPendingClaims();
}