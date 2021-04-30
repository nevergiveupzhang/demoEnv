package com.example.demo.java.jvm;

/**
 * -Xss
 */
public class XssDemo {
    private static int count = 10000;
    public static void main(String[] args) {
        call();
    }

    private static void call() {
        byte [] mb=new byte[10*1024*1024];
        System.out.println(--count);
        if(count > 0){
            call();
        }
    }
}
