package com.example.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerDemo2 implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("demo2 servlet context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("customContextParam="+arg0.getServletContext().getInitParameter("customContextParam"));
		System.out.println("demo2 servlet context init");
	}

}
