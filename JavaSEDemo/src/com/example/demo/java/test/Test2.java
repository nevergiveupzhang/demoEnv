package com.example.demo.java.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    private int i = 8;

    public Test2() throws InterruptedException {
        System.out.println(this.i);
        new Thread(()->{
            System.out.println(this.i);
        }).start();
    }
    public static void main(String[] args ) throws InterruptedException {
        new Test2();
    }

    public static int f(){
        int a=1;
        int b=2;
        try{
            return a;
        }finally {
            a++;
            b++;
        }
    }

}
