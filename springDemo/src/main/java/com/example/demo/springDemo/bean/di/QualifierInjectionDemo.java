package com.example.demo.springDemo.bean.di;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class QualifierInjectionDemo {
    @Bean
    @Qualifier
    public User user3(){
        User user = new User();
        user.setId(3);
        user.setName("user3");
        return user;
    }

    @Bean
    public User user4(){
        User user = new User();
        user.setId(4);
        user.setName("user4");
        return user;
    }

    @Autowired
    private List<User> allUsers;

    @Autowired
    @Qualifier
    private List<User> qualifierUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierInjectionDemo.class);
        applicationContext.refresh();

        QualifierInjectionDemo qualifierInjectionDemo = applicationContext.getBean(QualifierInjectionDemo.class);
        System.out.println("allUsers : "+qualifierInjectionDemo.allUsers);
        System.out.println("qualifierUsers : "+qualifierInjectionDemo.qualifierUsers);
        applicationContext.close();
    }
}
