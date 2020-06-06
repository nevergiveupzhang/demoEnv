package com.example.demo.springDemo.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.springDemo.entity.User;

public class BeanInstaniationDemo {
	public static void main(String[] args) {
		BeanFactory beanFactory=new ClassPathXmlApplicationContext("bean-creation-context.xml");
		User user1=beanFactory.getBean("user-by-static-method",User.class);
		User user2=beanFactory.getBean("user-by-instance-method",User.class);
		
		System.out.println(user1);
		System.out.println(user2);
	}
}
