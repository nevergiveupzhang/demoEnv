package com.example.demo.springDemo.bean.definition;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.springDemo.bean.factory.UserFactory;
import com.example.demo.springDemo.entity.User;

public class BeanInstaniationDemo {
	public static void main(String[] args) {
		BeanFactory beanFactory=new ClassPathXmlApplicationContext("bean-creation-context.xml");
		
//		createByStaticMethod(beanFactory);
		
//		createByInstanceMethod(beanFactory);
		
//		createByServiceLoader();

		createByServiceLoaderFactoryBean(beanFactory);
		
	}

	private static void createByServiceLoaderFactoryBean(BeanFactory beanFactory) {
		ServiceLoader<UserFactory> serviceLoader=beanFactory.getBean("userFactoryServiceLoader",ServiceLoader.class);
		Iterator<UserFactory> itr=serviceLoader.iterator();
		while(itr.hasNext()){
			UserFactory factory=itr.next();
			User user=factory.createUser();
			System.out.println(user);
		}
	}

	private static void createByServiceLoader() {
		ServiceLoader<UserFactory> serviceLoader=ServiceLoader.load(UserFactory.class,Thread.currentThread().getContextClassLoader());
		Iterator<UserFactory> itr=serviceLoader.iterator();
		while(itr.hasNext()){
			UserFactory factory=itr.next();
			User user=factory.createUser();
			System.out.println(user);
		}
	}

	private static void createByInstanceMethod(BeanFactory beanFactory) {
		User user2=beanFactory.getBean("user-by-instance-method",User.class);
		System.out.println(user2);
	}

	private static void createByStaticMethod(BeanFactory beanFactory) {
		User user1=beanFactory.getBean("user-by-static-method",User.class);
		System.out.println(user1);
	}
}
