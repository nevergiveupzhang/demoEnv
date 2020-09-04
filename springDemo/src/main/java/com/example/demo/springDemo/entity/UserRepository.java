package com.example.demo.springDemo.entity;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.Collection;
public class UserRepository {
    private Collection<User> users;
    private BeanFactory beanFactory;
    private ObjectFactory<BeanFactory> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<BeanFactory> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<BeanFactory> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
