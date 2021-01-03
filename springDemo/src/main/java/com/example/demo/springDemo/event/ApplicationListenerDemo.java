package com.example.demo.springDemo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;

public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        //方法一：基于接口注册事件
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(event);
            }
        });

        //方法二：基于注解注册事件
        applicationContext.register(ApplicationListenerDemo.class);
        applicationContext.register(MyApplicationListener.class);

        applicationContext.refresh();
        applicationContext.start();
        applicationContext.stop();
        applicationContext.close();
    }

    @EventListener
    @Async //异步事件
    public void onApplicationEventASync(ContextRefreshedEvent event){
        System.out.println(event);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        System.out.println(event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event){
        System.out.println(event);
    }

    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event){
        System.out.println(event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event){
        System.out.println(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent(""){});
    }

    static class MyApplicationListener implements ApplicationListener{

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println(event);
        }
    }
}
