package com.example.demo.java;

import java.lang.reflect.Method;

public class ReflectDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c=Class.forName("com.example.demo.java.Holder");
		for(Method m:c.getMethods()) {
			System.out.println(m.toString());
		}
	}

}
