package com.example.demo.springDemo.bean.scope.custom;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {
    public final static String SCOPE_NAME = "thread-local";
    private NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal("thread-local-scope"){
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = threadLocal.get();
        Object bean = context.get(name);
        if(bean == null){
            bean = objectFactory.getObject();
            context.put(name,bean);
        }
        return bean;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = threadLocal.get();
        Object bean = context.remove(name);
        return bean;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return  threadLocal.get().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
