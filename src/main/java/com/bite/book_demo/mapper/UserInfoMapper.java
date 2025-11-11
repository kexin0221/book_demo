package com.bite.book_demo.mapper;

import com.bite.book_demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where user_name = #{name}")
    UserInfo queryUserByName(String name);

}
