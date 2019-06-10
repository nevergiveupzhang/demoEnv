package com.example.demo.java;

public class ClassForNameDemo {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class c=Holder.class;
		System.out.println(c.getName());
		c.newInstance();
		c.getMethods();
//		Class.forName("com.example.demo.java.Holder");
	}
}
