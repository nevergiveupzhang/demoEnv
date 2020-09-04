package com.example.demo.springDemo.bean.lookup;

import com.example.demo.springDemo.bean.definition.AnnotationBeanDefinitionDemo;
import com.example.demo.springDemo.entity.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListableLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ListableLookupDemo.class);
        applicationContext.refresh();

    }
}
