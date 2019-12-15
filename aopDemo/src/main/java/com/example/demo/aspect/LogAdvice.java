package com.example.demo.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private final Logger LOGGER=Logger.getLogger(LogAdvice.class);
	
	@Around("execution(* com.example.demo.service.UserService.*(..))")
	public Object calcServiceExecTime(ProceedingJoinPoint joinPoint) throws Throwable {
		return printExecTime(joinPoint);
	}
	private Object printExecTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime=System.currentTimeMillis();
		StringBuilder timeBuilder = new StringBuilder();
		Object result=joinPoint.proceed();
		timeBuilder.append("print execution time=>");
		timeBuilder.append("["
				+ joinPoint.getSignature().getDeclaringTypeName() + ".");
		timeBuilder.append(joinPoint.getSignature().getName()+"()");
		long endTime=System.currentTimeMillis();
		timeBuilder.append(" took "+(endTime-startTime)+"ms]");
		LOGGER.info(timeBuilder.toString());
		return result;
	}
}
