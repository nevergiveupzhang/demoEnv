package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	@Before("execution(* com.example.demo.service.*.*(..))")
	public void add(JoinPoint join) {
		Object[] args =join.getArgs();
		for(Object obj:args) {
			System.out.println(obj);
		}
	}
}
