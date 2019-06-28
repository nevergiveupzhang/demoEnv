package com.example.demo.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/spring")
public class SpringDataRedisController {
	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public String create(HttpServletRequest request) {
		long startTime=System.currentTimeMillis();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username=" + username + "&password=" + password);
		ValueOperations<String, Serializable> ops=redisCacheTemplate.opsForValue();
		for(int i=0;i<10000000;i++) {
			ops.set(username+i, new User(username+i, password+i));;
		}
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-startTime)+"ms");
		return "success";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Object get(String username) {
		long startTime=System.currentTimeMillis();
		User user=(User) redisCacheTemplate.opsForValue().get(username);
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-startTime)+"ms");
		return user;
	}

}
