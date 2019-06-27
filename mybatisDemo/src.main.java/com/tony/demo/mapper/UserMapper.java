package com.tony.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tony.demo.bean.User;

public interface UserMapper {
	List<User> getUsers(@Param("username") String username, @Param("age") String age);
}
