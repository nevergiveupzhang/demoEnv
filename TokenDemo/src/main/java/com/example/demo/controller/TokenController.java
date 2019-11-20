package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.utils.Token;

public class TokenController {
	private static final String SESSION_TOKEN="SESSION_TOKEN";
	@RequestMapping(value = "/login")
	public Map<String,Object> getLogin(HttpServletRequest request, HttpServletResponse response,String loginName,String password){
	    String zhangxing = Token.genetateToken();
	    request.getSession().setAttribute(SESSION_TOKEN,zhangxing);

	    boolean login = false;
	    //储存token
	    String pwd = loginService.getPassword(loginName);
	    Map<String,Object> map = new HashMap<String, Object>();
	    if(pwd.equals(password)){
	        login = true;
	    }
	    map.put("login",login);
	    map.put("token",zhangxing);
	    return map;
	}
}
