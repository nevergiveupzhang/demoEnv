package com.example.demo.springDemo.bean.scope;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
/**
 * 有一个singleton，三个prototype
 **/
public class ScopeDemo implements DisposableBean {
    @Bean
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }
    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ScopeDemo.class);
        applicationContext.refresh();

        scopeByLookup(applicationContext);
        scopeByInject(applicationContext);

        applicationContext.close();
    }

    private static void scopeByInject(AnnotationConfigApplicationContext applicationContext) {
        ScopeDemo scopeDemo = applicationContext.getBean(ScopeDemo.class);
        System.out.println("singletonUser1="+scopeDemo.singletonUser1);
        System.out.println("singletonUser2="+scopeDemo.singletonUser2);
        System.out.println("prototypeUser1="+scopeDemo.prototypeUser1);
        System.out.println("prototypeUser2="+scopeDemo.prototypeUser2);
        System.out.println("users="+scopeDemo.users);
    }

    private static void scopeByLookup(AnnotationConfigApplicationContext applicationContext) {
        for(int i=0;i<3;i++){
            System.out.println("singletonUser="+applicationContext.getBean("singletonUser"));
            System.out.println("prototypeUser="+applicationContext.getBean("prototypeUser"));
        }
    }

    @Override
    public void destroy(){
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for(Map.Entry<String,User> entry : this.users.entrySet()){
            String beanName = entry.getKey();
            if(beanFactory.getBeanDefinition(beanName).isPrototype()){
                User user = entry.getValue();
                user.destroy();
            }
        }
    }
}
