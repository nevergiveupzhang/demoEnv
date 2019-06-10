package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import redis.clients.jedis.Jedis;

@Controller
public class UserController {
	@RequestMapping(value = "/createUser1", method = RequestMethod.GET)
	public String createUser1(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username=" + username + "&password=" + password);
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.connect();
		jedis.set(username, password);
		jedis.disconnect();
		return "";
	}
	
	@RequestMapping(value = "/createUser2", method = RequestMethod.GET)
	public String createUser2(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println("username=" + username + "&password=" + password);
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.connect();
		jedis.set(username, password);
		jedis.disconnect();
		return "";
	}

}
