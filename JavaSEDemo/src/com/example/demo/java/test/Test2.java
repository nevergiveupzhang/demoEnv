package com.example.demo.java.test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test2 {
    private static Random random = new Random();

    public static void main(String[] args ) {
        byMap();
        byList();
    }

    private static void byList() {
        List<Map<String,Integer>> list = generateMap2();
        long start =System.currentTimeMillis();
        for(int i=0;i<200000;i++){
            for(Map<String,Integer> map : list){
                if(map.get("test"+random.nextInt(100)) != null){
                    break;
                }
            }
        }
        System.out.println("byList took "+(System.currentTimeMillis()-start)+"ms");
    }

    private static void byMap() {
        Map<String,Integer> map = generateMap();
        long start =System.currentTimeMillis();
        for(int i=0;i<200000;i++){
            map.get("test"+random.nextInt(100));
        }
        System.out.println("byMap took "+(System.currentTimeMillis()-start)+"ms");
    }

    private static Map<String, Integer> generateMap() {
        long start = System.currentTimeMillis();
        Map<String,Integer> ret = new HashMap<>(200000);
        for(int i=0;i<100;i++){
            ret.put("test"+i,i);
        }
        System.out.println("generateMap took "+(System.currentTimeMillis()-start)+"ms");
        return ret;
    }

    private static List<Map<String, Integer>> generateMap2() {
        long start = System.currentTimeMillis();
        List<Map<String, Integer>> ret = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<100;i++){
            if(i % 50000 == 0){
                map = new HashMap<>(50000);
                ret.add(map);
            }
            map.put("test"+i,i);
        }
        System.out.println("generateMap2 took "+(System.currentTimeMillis()-start)+"ms");
        return ret;
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
