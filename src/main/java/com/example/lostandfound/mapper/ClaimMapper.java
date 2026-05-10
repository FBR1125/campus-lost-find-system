package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Claim;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClaimMapper {

    @Select("SELECT * FROM claim WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "message", column = "message"),
            @Result(property = "status", column = "status"),
            @Result(property = "createTime", column = "create_time")
    })
    Claim getById(Integer id);

    @Delete("DELETE FROM claim WHERE id = #{id}")
    void deleteById(Integer id);
}