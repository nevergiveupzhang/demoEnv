package com.example.demo.java.test;

import java.text.NumberFormat;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();
		//这样就去掉了.0
		String str = nf.format(1.9851005E7);
		//去掉逗号
		str=str.replace(",","");
		System.out.println(str);
	}

}
