package com.example.demo.springDemo.bean.annotation.autowired.custom;

import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
