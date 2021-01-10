package com.example.demo.springDemo.bean.annotation.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {
    //隐形别名
    @AliasFor(annotation = ComponentScan.class,attribute = "basePackages")
    String[] myBasePackages() default {};
}
