package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Claim;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClaimMapper {

    @Select("SELECT * FROM claim WHERE id = #{id}")
    Claim getById(Integer id);

    @Delete("DELETE FROM claim WHERE id = #{id}")
    void deleteById(Integer id);
}