package com.tony.demo.service;

import java.util.List;

import com.tony.demo.bean.User;

public interface IUserService {
	List<User> getUsers(String username, String age);
}
