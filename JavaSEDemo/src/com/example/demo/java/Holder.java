package com.example.demo.java;

public class Holder implements IHolder{
	static {
		System.out.println("i am static block");
	}
	public static void print() {
		System.out.println("hello");
	}
	
	public void method1() {
		System.out.println("i am real Holder");
	}
	
	public void method2(String arg0) {
		
	}
	
	private void method3() {}
	
	void method4() {}
}
