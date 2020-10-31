package com.example.demo.springDemo.bean.circular;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class CircularDependencyDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CircularDependencyDemo.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean("user"));
        System.out.println(applicationContext.getBean("classRoom"));

    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        return user;
    }

    @Bean
    public ClassRoom classRoom(){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(1);
        classRoom.setName("教室1");
        return classRoom;
    }
}
