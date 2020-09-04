package com.example.demo.springDemo.bean.di;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AnnotationDependencyResovleDemo {
    @Autowired
    private User user;
    @Autowired
    private List<User> allUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyResovleDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:bean-lookup-context.xml");

        applicationContext.refresh();


    }
}
