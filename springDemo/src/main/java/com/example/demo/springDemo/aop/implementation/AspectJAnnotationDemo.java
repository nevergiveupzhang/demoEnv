package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.AspectPointcutConfiguration;
import com.example.demo.springDemo.aop.common.DefaultEchoService;
import com.example.demo.springDemo.aop.common.IEchoService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotationDemo.class);
        applicationContext.register(AspectPointcutConfiguration.class);
        applicationContext.refresh();

        AspectJAnnotationDemo demo = applicationContext.getBean(AspectJAnnotationDemo.class);
        System.out.println(demo);

        IEchoService echoService = applicationContext.getBean(IEchoService.class);
        System.out.println(echoService);
        applicationContext.close();
    }

    @Bean
    public DefaultEchoService echoService(){
        return new DefaultEchoService();
    }
}
