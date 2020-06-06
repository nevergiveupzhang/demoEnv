package com.example.demo.SyncDemo.test;

import org.openjdk.jol.info.ClassLayout;

import com.example.demo.SyncDemo.entity.A;

public class Test {

	public static void main(String[] args) {
		A a=new A();
		System.out.println(ClassLayout.parseInstance(a).toPrintable());
	}

}
