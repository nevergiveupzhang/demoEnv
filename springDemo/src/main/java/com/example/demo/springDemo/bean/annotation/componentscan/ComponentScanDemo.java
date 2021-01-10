package com.example.demo.springDemo.bean.annotation.componentscan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.AntPathMatcher;

@MyComponentScan2(myBasePackages2 = "com.example.demo.springDemo.bean.annotation.componentscan")
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ComponentScanDemo.class);

        applicationContext.refresh();

        System.out.println(applicationContext.getBean(User.class));

        applicationContext.close();

//        AntPathMatcher matcher = new AntPathMatcher();
//        boolean s = matcher.match("D:/Code/demoEnv/springDemo/target/classes/com/example/demo/springDemo/bean/annotation/componentscan/**/*.class","D:/Code/demoEnv/springDemo/target/classes/com/example/demo/springDemo/bean/annotation/componentscan/ComponentScanDemo.class");
//        System.out.println(s);
    }
}
