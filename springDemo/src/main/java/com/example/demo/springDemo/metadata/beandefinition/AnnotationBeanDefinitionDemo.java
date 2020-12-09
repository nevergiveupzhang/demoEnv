package com.example.demo.springDemo.metadata.beandefinition;

import com.example.demo.springDemo.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("user",User.class);
        System.out.println(user);
    }

    public static class Config{
        @Bean
        public User user(){
            User user = new User();
            user.setName("create-by-annotation-config");
            return user;
        }
    }

}
