package com.example.demo.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载yaml配置文件的方法
 * spring-boot更新到1.5.2版本后locations属性无法使用
 * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 * 故现在把数据放到application.yml文件中,spring-boot启动时会加载
 */
@Component
@ConfigurationProperties(prefix="mybar")
public class Bar {
    private String name;

    private String[] arrs;

    private List<Map<String,String>> nameList;
    
    private List<String> barNameList;

    private Map<String,String> map;

    public String getName() {
        return name;
    }

    //String类型的一定需要setter来接收属性值；maps, collections, 和 arrays 不需要
    public void setName(String name) {
        this.name = name;
    }

    public String[] getArrs() {
        return arrs;
    }

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Map<String, String>> getNameList() {
        return nameList;
    }

    public void setNameList(List<Map<String, String>> nameList) {
        this.nameList = nameList;
    }

    public List<String> getBarNameList() {
        return barNameList;
    }

    public void setBarNameList(List<String> barNameList) {
        this.barNameList = barNameList;
    }

    
}