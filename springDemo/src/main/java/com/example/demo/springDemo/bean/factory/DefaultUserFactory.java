package com.example.demo.springDemo.bean.factory;

import com.example.demo.springDemo.entity.User;

public class DefaultUserFactory implements UserFactory {
    public User createUser(){
        User user=new User();
        user.setName("user-by-factory-bean-factory-method");
        return user;
    }
}
