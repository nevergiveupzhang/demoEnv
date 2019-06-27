package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.ReportConfiguration;
import com.example.demo.util.DataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/")
public class ApplicationYmlController {
	@Autowired
	private ReportConfiguration config;
	@RequestMapping("/")
	@ResponseBody
	public String index() throws JsonProcessingException {
		System.out.println(DataUtil.toJsonStr(config));
		return DataUtil.toJsonStr(config);
	}
}
