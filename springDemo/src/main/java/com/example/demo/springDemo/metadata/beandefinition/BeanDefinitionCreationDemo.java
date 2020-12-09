package com.example.demo.springDemo.metadata.beandefinition;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();

        //第一种
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","beanDefinitionUser");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        applicationContext.registerBeanDefinition("beanDefinitionUser",beanDefinition);
        System.out.println(applicationContext.getBean("beanDefinitionUser"));

        //第二种
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("name","genericBeanDefinitionUser");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        applicationContext.registerBeanDefinition("genericBeanDefinitionUser",genericBeanDefinition);
        System.out.println(applicationContext.getBean("genericBeanDefinitionUser"));

    }
}
