package com.example.demo.springDemo.metadata.beandefinitionreader;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotatedBeanDefinitionReaderDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        System.out.println(beanDefinitionCountAfter - beanDefinitionCountBefore);
        System.out.println(beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionReaderDemo.class));

    }
}
