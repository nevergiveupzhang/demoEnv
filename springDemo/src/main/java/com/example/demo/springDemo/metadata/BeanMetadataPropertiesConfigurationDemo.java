package com.example.demo.springDemo.metadata;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMetadataPropertiesConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        EncodedResource resource = new EncodedResource(new ClassPathResource("META-INF/user.properties"),"GBK");
        reader.loadBeanDefinitions(resource);
        System.out.println(beanFactory.getBean("user",User.class));
    }
}
