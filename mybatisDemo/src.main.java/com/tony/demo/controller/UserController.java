package com.tony.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tony.demo.bean.User;
import com.tony.demo.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService uerService;
 
	@RequestMapping("/users")
	@ResponseBody
	public String getUser(@RequestParam String username,@RequestParam String age) {
		List<User> users = uerService.getUsers(username,age);
		return users.toString();
	}
	
}
