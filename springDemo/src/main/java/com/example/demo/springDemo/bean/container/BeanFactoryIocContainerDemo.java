package com.example.demo.springDemo.bean.container;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryIocContainerDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:bean-creation-context.xml";
        int beanDefinitions = reader.loadBeanDefinitions(location);
        System.out.println(beanDefinitions);
        System.out.println(beanFactory.getBean("user-by-static-method-alias"));
    }
}
