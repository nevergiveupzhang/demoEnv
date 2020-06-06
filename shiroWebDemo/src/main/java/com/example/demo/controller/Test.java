package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import junit.framework.Assert;

public class Test {
	public static void main(String[] args) {
		Factory<org.apache.shiro.mgt.SecurityManager> factory =
	            new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1213");
	    try {
	        //4、登录，即身份验证
	        subject.login(token);
	    } catch (AuthenticationException e) {
	        //5、身份验证失败
	    	e.printStackTrace();
	    }
	    
	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
	    //6、退出
	    subject.logout();
	}
}
