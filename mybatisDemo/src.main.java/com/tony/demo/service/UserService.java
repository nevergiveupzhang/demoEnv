package com.tony.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tony.demo.bean.User;
import com.tony.demo.mapper.UserMapper;

@Service("userService")
public class UserService  {
	@Autowired
	private UserMapper userMapper;

	public void createUser(User user) {
		userMapper.createUser(user);
	}

	public User getById(int id) {
		return userMapper.getById(id);
	}

	public List<User> getAll() {
		return userMapper.getAll();
	}

	public List<User> getUsers(Map<String, Object> params) {
		return userMapper.getUsers(params);
	}
	
 
}
