package com.example.demo.springDemo.bean.lifecycle;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class PostProcessAfterInstantiationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-context.xml");
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        System.out.println(beanFactory.getBean("user1"));
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName,"user1") && User.class.equals(bean.getClass())){
                User user = (User) bean;
                user.setName("zhangsan v2");
                return false;
            }
            return true;
        }
    }
}
