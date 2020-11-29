package com.example.demo.springDemo.bean.scope.web;

import com.example.demo.springDemo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class WebMvcConfiguration {
    private int count = 0;
    @Bean
    @Scope("prototype")
//    @RequestScope
//    @SessionScope
//    @ApplicationScope
    public User user(){
        User user = new User();
        user.setId(System.nanoTime());
        user.setName("zhangsan"+(++count));
        return user;
    }
}
