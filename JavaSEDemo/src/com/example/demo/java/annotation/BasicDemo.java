package com.example.demo.java.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BasicDemo {
	public static void main(String[] args) {
		Class cls = User.class;

		while(cls != null){
			Field []fields = cls.getDeclaredFields();
			for(Field field : fields){
				System.out.println(field.getName());
			}
			cls = cls.getSuperclass();
		}

	}
}
