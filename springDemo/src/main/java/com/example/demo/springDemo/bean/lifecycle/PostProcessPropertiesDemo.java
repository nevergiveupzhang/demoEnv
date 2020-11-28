package com.example.demo.springDemo.bean.lifecycle;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class PostProcessPropertiesDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-context.xml");
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        System.out.println(beanFactory.getBean("user1"));
    }
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("user1",beanName) && User.class.equals(bean.getClass())){
                final MutablePropertyValues mpv;
                if(pvs instanceof MutablePropertyValues){
                   mpv = (MutablePropertyValues) pvs;
                } else{
                    mpv = new MutablePropertyValues();
                }
                if(mpv.contains("name")){
                    mpv.removePropertyValue("name");
                    mpv.addPropertyValue("name","zhangsan v2");
                }
                return mpv;
            }
            return null;
        }
    }
}
