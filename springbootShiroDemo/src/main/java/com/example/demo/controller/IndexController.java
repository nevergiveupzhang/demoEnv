package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("index","model","");
	}
	@RequestMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("home","model","");
	}
}
