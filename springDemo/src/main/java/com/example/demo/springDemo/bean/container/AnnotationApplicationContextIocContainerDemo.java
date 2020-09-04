package com.example.demo.springDemo.bean.container;

import com.example.demo.springDemo.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationApplicationContextIocContainerDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextIocContainerDemo.class);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                applicationContext.refresh();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                User user = applicationContext.getBean("user", User.class);
                System.out.println(user);
            }
        });
        t2.start();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("create-by-annotation-config");
        throw new RuntimeException();
    }
}
