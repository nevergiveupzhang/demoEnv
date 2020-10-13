package com.example.demo.java.classloader;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader();
        Object obj = classLoader.loadClass("User").newInstance();

        System.out.println(obj instanceof User);
    }
}
