package com.example.demo.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class ServletRequestListenerDemo implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("request listener destroyed");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request listener init");
	}

}
