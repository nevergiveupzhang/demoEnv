package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.model.MapApplicationConfigModel;
import com.example.demo.config.model.ReportApplicationConfigModel;
import com.example.demo.util.ApplicationContextManager;
import com.example.demo.util.DataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/")
public class ApplicationYmlController {
	@Autowired
	private ReportApplicationConfigModel config;
	@Autowired
	private MapApplicationConfigModel mapConfig;
	@RequestMapping("/")
	@ResponseBody
	public String index() throws JsonProcessingException {
		System.out.println(DataUtil.toJsonStr(config));
		return DataUtil.toJsonStr(config);
	}
	
	@RequestMapping("/map")
	@ResponseBody
	public String map() throws JsonProcessingException {
		System.out.println(DataUtil.toJsonStr(mapConfig));
		return DataUtil.toJsonStr(mapConfig);
	}
	@RequestMapping("/context")
	@ResponseBody
	public String context() throws JsonProcessingException {
		ApplicationYmlController bean=ApplicationContextManager.getServiceByName("applicationYmlController", ApplicationYmlController.class);
		return DataUtil.toJsonStr(bean.mapConfig);
	}

	@RequestMapping("/jsp")
	public String jsp(){
		return "user";
	}
}
