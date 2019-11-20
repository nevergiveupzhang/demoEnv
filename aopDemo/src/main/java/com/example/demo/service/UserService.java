package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void login(String userName) {
		userMapper.create(new User(userName));
	}

}
