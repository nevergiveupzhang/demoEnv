package com.example.demo.springDemo.bean.factory;

import com.example.demo.springDemo.entity.User;

public interface UserFactory {
	default User createUser(){
		User user=new User();
		user.setName("user-by-instance-method");
		return user;
	}
}
