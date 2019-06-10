package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@Value("${randomValue}")
	private String random;

	@RequestMapping("/{username}/{password}")
	ModelAndView home(@PathVariable String username,@PathVariable String password) {
		System.out.println(username);
		System.out.println(password);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "zhangsan");
		model.put("age", 28);
		return new ModelAndView("/index","model",model);
	}

	@RequestMapping("/wel")
	@ResponseBody
	String welcome() {
		return "welcome!";
	}

	@RequestMapping("${customPath}")
	@ResponseBody
	String custom() {
		return "customPath!";
	}

	@RequestMapping("/random")
	@ResponseBody
	String random() {
		return random;
	}

}
