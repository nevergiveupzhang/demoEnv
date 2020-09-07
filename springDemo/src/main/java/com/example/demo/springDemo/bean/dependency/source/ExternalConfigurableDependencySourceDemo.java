package com.example.demo.springDemo.bean.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
@Configuration
@PropertySource("classpath:default.properties")
public class ExternalConfigurableDependencySourceDemo {
    @Value("${id}")
    private Long id;
    @Value("${name}")
    private String name;

    @Value("classpath:default.properties")
    private Resource resource;

    @PostConstruct
    public void init(){
        System.out.println("id="+id+"&name="+name);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurableDependencySourceDemo.class);
        applicationContext.refresh();
    }
}
