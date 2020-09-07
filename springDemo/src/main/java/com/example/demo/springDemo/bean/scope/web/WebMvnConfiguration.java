package com.example.demo.springDemo.bean.scope.web;

import com.example.demo.springDemo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class WebMvnConfiguration {
    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope
    public User user(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }
}
