package com.example.demo.springDemo.bean.creation;

import com.example.demo.springDemo.entity.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("bean-di-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);
        System.out.println(userRepository.getBeanFactory() == userRepository.getObjectFactory().getObject());
    }
}
