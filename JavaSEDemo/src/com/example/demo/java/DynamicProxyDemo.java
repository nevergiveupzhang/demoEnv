package com.example.demo.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;


public class DynamicProxyDemo implements InvocationHandler{
	private static Logger logger = Logger.getLogger(DynamicProxyDemo.class.getName());
	private Object proxy;
	public DynamicProxyDemo(Object proxy) {
		this.proxy=proxy;
	}
	public static void main(String[] args) {
		IHolder real=new Holder();
		InvocationHandler handler = new DynamicProxyDemo(real);
		IHolder holder=(IHolder) Proxy.newProxyInstance(IHolder.class.getClassLoader(), new Class<?>[] {IHolder.class},handler);
		holder.method1();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("i am proxy");
		logger.info("i am proxy");
		return method.invoke(proxy, args);
	}

}
