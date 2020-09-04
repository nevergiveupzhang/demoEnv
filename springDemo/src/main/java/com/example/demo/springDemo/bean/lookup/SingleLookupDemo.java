package com.example.demo.springDemo.bean.lookup;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingleLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SingleLookupDemo.class);
        applicationContext.refresh();

        ObjectFactory<String> objectFactory = applicationContext.getBeanProvider(String.class);
        System.out.println(objectFactory.getObject());
    }
    @Bean
    public String str1(){
        return "hello";
    }
}
