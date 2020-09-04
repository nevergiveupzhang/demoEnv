package com.example.demo.springDemo.customAutowired;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomAutowiredDemo {
    @CustomAutowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:bean-lookup-context.xml");

        applicationContext.register(CustomAutowiredDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(CustomAutowiredDemo.class).user);
    }
}
