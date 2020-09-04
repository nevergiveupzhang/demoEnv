package com.example.demo.springDemo.bean.container;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlApplicationContextIocContainerDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-lookup-context.xml");
        System.out.println(applicationContext.getBean("user1"));
    }
}
