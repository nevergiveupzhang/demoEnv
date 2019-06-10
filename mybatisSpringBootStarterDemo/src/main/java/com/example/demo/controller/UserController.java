package com.example.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.IUserService;

@Controller
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/users")
	public ModelAndView showUsers() {
		System.out.println("i am in controller!");
		ModelAndView view=new ModelAndView("users");
		System.out.println(userService.getUsers());
		view.addObject("users",userService.getUsers());
		return view;
	}
}
