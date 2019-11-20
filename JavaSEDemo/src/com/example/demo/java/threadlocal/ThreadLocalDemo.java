package com.example.demo.java.threadlocal;

public class ThreadLocalDemo {
	public static void main(String[] args) {
		ThreadLocal tl=new ThreadLocal();
		tl.set(1);
	}
}
