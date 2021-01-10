package com.example.demo.springDemo.bean.annotation.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class,attribute = "myBasePackages")
    String[] myBasePackages2() default {};
}
