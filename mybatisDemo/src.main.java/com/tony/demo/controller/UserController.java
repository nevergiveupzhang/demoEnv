package com.tony.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tony.demo.bean.User;
import com.tony.demo.service.IUserService;

@Controller
@PropertySource(value = { "classpath:jdbc2.properties" })
public class UserController {
	@Value("${jdbc2.driver}")
	private String driverClassName;
	@Autowired
	private IUserService uerService;
 
	@RequestMapping("/users")
	@ResponseBody
	public String getUser() {
		System.out.println(driverClassName);
		List<User> users = uerService.getAllUser();
		return users.toString();
	}
}
