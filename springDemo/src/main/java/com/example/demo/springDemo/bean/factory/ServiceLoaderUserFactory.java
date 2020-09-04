package com.example.demo.springDemo.bean.factory;

import com.example.demo.springDemo.entity.User;

public class ServiceLoaderUserFactory implements UserFactory {
    public User createUser(){
        User user=new User();
        user.setName("user-by-service-loader");
        return user;
    }
}
