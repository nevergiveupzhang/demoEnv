package com.example.demo.springDemo.bean.scope.custom;

import com.example.demo.springDemo.bean.scope.ScopeDemo;
import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ThreadLocalScopeDemo {
    @Bean
    @ThreadScopeAnnotation
    public static User threadLocalUser(){
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
        });
        applicationContext.refresh();

        scopeByLookup(applicationContext);

        applicationContext.close();
    }

    private static void scopeByLookup(AnnotationConfigApplicationContext applicationContext) {
        for(int i=0;i<3;i++){
            Thread t = new Thread(() -> {
                System.out.println("threadLocalUser="+applicationContext.getBean("threadLocalUser",User.class));
            });
            t.start();
        }
    }
}
