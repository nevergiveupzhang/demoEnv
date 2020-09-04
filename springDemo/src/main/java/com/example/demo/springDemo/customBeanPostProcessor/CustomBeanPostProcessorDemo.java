package com.example.demo.springDemo.customBeanPostProcessor;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomBeanPostProcessorDemo {
    @InjectedUser
    private User injectedUser;
    @Autowired
    private User user;

    @Bean
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>(Arrays.asList(Autowired.class,InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomBeanPostProcessorDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:bean-lookup-context.xml");
        applicationContext.refresh();
        CustomBeanPostProcessorDemo customBeanPostProcessorDemo = applicationContext.getBean(CustomBeanPostProcessorDemo.class);
        System.out.println(customBeanPostProcessorDemo.user);
        System.out.println(customBeanPostProcessorDemo.injectedUser);
    }
}
