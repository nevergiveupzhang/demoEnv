package com.example.demo.springDemo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        //1、创建parent spring应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        //2、创建current spring应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);

        //3、current -> parent
        currentContext.setParent(parentContext);

        parentContext.refresh();
        currentContext.refresh();

        currentContext.close();
        parentContext.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent>{

        private static Set<ApplicationContextEvent> processedEvent = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if(processedEvent.add(event)){
                System.out.println(event.getApplicationContext().getId() + ":" + event.getClass().getSimpleName());
            }
        }
    }
}
