package com.example.demo.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class HttpSessionBindingListenerDemo implements HttpSessionBindingListener {
	
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("value bound");
//		System.out.println(arg0.getSource().toString());
//		System.out.println(arg0.getValue().toString());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("value unbound");
	}

}
