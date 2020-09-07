package com.example.demo.springDemo.bean.scope.custom;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(ThreadLocalScope.SCOPE_NAME)
public @interface ThreadScopeAnnotation {
}
