package com.example.demo.java.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args ) {
        Integer i = 1;
        int h;
        System.out.println((h = i.hashCode()) ^ (h >>> 16));

        String s = "1";
        System.out.println((h = s.hashCode()) ^ (h >>> 16));

        System.out.println(s.equals(i));
    }



    private String match(String content,String reg){
        String fieldValue="";
        Pattern pattern = Pattern.compile(reg);// ƥ���ģʽ
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int i = 1;
            fieldValue=matcher.group(i);
            i++;
        }
        return fieldValue;
    }

}
