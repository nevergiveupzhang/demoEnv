package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringUtil {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<>();
		for(int i=0;i<10000000;i++) {
			map.put(getRandomString(10), "");
		}
		
		long start,end;
		start=System.currentTimeMillis();
		for(Map.Entry<String,String> entry:map.entrySet()) {
			entry.getValue();
		}
		end= System.currentTimeMillis();
		System.out.println(end-start);
		
		start=System.currentTimeMillis();
		Collection<String> c=map.values();
		for(String entry:c) {
			
		}
		end= System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
}
