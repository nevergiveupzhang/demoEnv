package com.example.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerDemo implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("servlet context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("customContextParam="+arg0.getServletContext().getInitParameter("customContextParam"));
		System.out.println("servlet context init");
	}

}
