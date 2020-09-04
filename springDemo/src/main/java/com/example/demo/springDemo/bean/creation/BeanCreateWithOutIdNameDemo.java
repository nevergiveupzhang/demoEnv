package com.example.demo.springDemo.bean.creation;

import com.example.demo.springDemo.entity.User;
import com.example.demo.springDemo.entity.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreateWithOutIdNameDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("bean-creation-context.xml");
        System.out.println(beanFactory.getBean("com.example.demo.springDemo.entity.User#0"));
        System.out.println(beanFactory.getBean("com.example.demo.springDemo.entity.User#1"));
    }
}
