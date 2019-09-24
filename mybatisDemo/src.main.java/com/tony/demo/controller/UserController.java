package com.tony.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tony.demo.bean.User;
import com.tony.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
 
	@RequestMapping("/create")
	@ResponseBody
	public String create(@RequestParam String name,@RequestParam int age) {
		userService.createUser(new User(name,age));
		return "success";
	}
	@RequestMapping("/query")
	@ResponseBody
	public String getUser(@RequestParam String name,@RequestParam int age) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("name", name);
		params.put("age", age);
		List<User> users = userService.getUsers(params);
		System.out.println(users.toString());
		return users.toString();
	}
	
	@RequestMapping("/byDao")
	@ResponseBody
	public String byDao() {
		List<Integer> ids=new ArrayList<Integer>(100000);
		for(int i=1;i<=100000;i++) {
			ids.add(i);
		}
		long start=System.currentTimeMillis();
		for(int id:ids) {
			userService.getById(id);
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/byMap")
	@ResponseBody
	public String byMap() {
		List<Integer> ids=new ArrayList<Integer>(100000);
		for(int i=1;i<=100000;i++) {
			ids.add(i);
		}
		long start=System.currentTimeMillis();
		List<User> users=userService.getAll();
		Map<Integer,User> userMap=new HashMap<Integer,User>();
		for(User user:users) {
			userMap.put(user.getId(), user);
		}
		for(int id:ids) {
			userMap.get(id);
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		return String.valueOf(end-start);
	}
}
