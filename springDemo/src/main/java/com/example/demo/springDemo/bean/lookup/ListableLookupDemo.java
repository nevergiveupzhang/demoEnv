package com.example.demo.springDemo.bean.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListableLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ListableLookupDemo.class);
        applicationContext.refresh();

    }
}
