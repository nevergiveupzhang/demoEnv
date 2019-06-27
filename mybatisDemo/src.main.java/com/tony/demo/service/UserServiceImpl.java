package com.tony.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tony.demo.bean.User;
import com.tony.demo.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUsers(String username, String age) {
		return userMapper.getUsers(username,age);
	}
	
 
}
