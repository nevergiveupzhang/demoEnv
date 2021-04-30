package com.example.demo.java.test;

import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private int a = 9;
    public static void main(String[] args ) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Test test = new Test();
        Field f = Test.class.getDeclaredField("a");
        f.setAccessible(true);
        System.out.println(f.get(test));
    }
}
