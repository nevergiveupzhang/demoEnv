package com.example.demo.springDemo.bean.annotation.autowired.extend;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExtendAutowiredDemo {
    @ExtendAutowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:bean-lookup-context.xml");

        applicationContext.register(ExtendAutowiredDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(ExtendAutowiredDemo.class).user);
    }
}
