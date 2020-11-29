package com.example.demo.springDemo.bean.lifecycle;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class AfterSingletonInstantiatedDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-context.xml");
        User user2 = beanFactory.getBean("user2",User.class);
        beanFactory.preInstantiateSingletons();
        System.out.println(user2);
    }

    public static class User implements SmartInitializingSingleton {
        private long id;
        private String name;

        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + "]";
        }

        @Override
        public void afterSingletonsInstantiated() {
            this.name = "zhangsan2 v2";
        }
    }
}
