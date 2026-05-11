package com.example.lostandfound.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
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
    @Select("SELECT id, user_id, name, place, description, contact, type, status, create_time, check_status AS checkStatus FROM item WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Item> myItems(@Param("userId") Integer userId);

    // 个人中心：我的认领
    @Select("SELECT id, item_id AS itemId, user_id, message, status, create_time FROM claim WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Claim> myClaims(@Param("userId") Integer userId);

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

    // 认领物品 → 更新 status = 1
    @Update("UPDATE item SET status = 1 WHERE id = #{itemId}")
    int updateItemStatus(@Param("itemId") Integer itemId);

    // 统计用户【已找回】物品数
    @Select("SELECT COUNT(*) FROM item WHERE user_id = #{userId} AND status = 1")
    int countUserFoundItems(@Param("userId") Integer userId);

    //管理员用户管理
    @Select("SELECT id, username, phone, password, create_time FROM user ORDER BY create_time DESC")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    List<User> selectAllUser();

    // 删除用户
    @Delete("DELETE FROM user WHERE id = #{userId}")
    void deleteById(Integer userId);

    // 重置密码为 123456
    @Update("UPDATE user SET password = '123456' WHERE id = #{userId}")
    void resetPassword(Integer userId);

    //
    @Update("UPDATE user SET username=#{username}, phone=#{phone} WHERE id=#{id}")
    void updateUserInfo(@Param("id") Integer id,
                        @Param("username") String username,
                        @Param("phone") String phone);

    // 删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUserById(@Param("id") Integer id);

}