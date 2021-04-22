package com.example.demo.java.test;

public class Test {
    private int m = 8;

    public Test() throws InterruptedException {
        System.out.println(this.m);
        new Thread(()->{
            System.out.println(this.m);
        }).start();
    }
    public static void main(String[] args ) throws InterruptedException {
        new Test();
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
