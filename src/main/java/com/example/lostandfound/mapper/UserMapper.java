package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(username, password, phone) VALUES(#{username}, #{password}, #{phone})")
    int insertUser(User user);
}