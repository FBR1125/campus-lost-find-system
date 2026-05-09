package com.example.lostandfound.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.Item;
import com.example.lostandfound.entity.Claim;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface UserMapper {
    //用户名是否相同
    @Select("SELECT * FROM user WHERE username=#{username}")
    User selectByUsername(String username);

    // 注册：包含 id、username、password、phone
    @Insert("INSERT INTO user(id, username, password, phone, create_time) VALUES(#{id}, #{username}, #{password}, #{phone}, NOW())")
    int register(User user);
    // 登录
    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectById(Integer id);
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

    // 根据id查密码
    @Select("select password from user where id = #{id}")
    String getPwdById(@Param("id") Integer id);

    // 修改密码
    @Update("update user set password = #{newPwd} where id = #{id}")
    int updatePwd(@Param("id") Integer id, @Param("newPwd") String newPwd);


}