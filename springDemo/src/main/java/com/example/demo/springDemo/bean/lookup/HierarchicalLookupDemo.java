package com.example.demo.springDemo.bean.lookup;

import com.example.demo.springDemo.bean.container.AnnotationApplicationContextIocContainerDemo;
import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class HierarchicalLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalLookupDemo.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.setParentBeanFactory(createParentBeanFactory());
        applicationContext.refresh();

        System.out.println(beanFactory.getParentBeanFactory().getBean("user-by-static-method"));
    }

    private static BeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:bean-creation-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
