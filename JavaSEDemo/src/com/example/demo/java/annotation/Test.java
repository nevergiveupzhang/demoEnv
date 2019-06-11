package com.example.demo.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	public String value();
}
