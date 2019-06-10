package com.tony.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.tony.demo.bean.User;
@ComponentScan(basePackages = "com.tony.demo")
@SpringBootApplication
public class MainApplication {
	@Bean
	public User userBean() {
		User user=new User();
		user.setId(1);
		user.setName("zhangsan");
		return user;
	}
	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(MainApplication.class, args);
		User user=context.getBean(User.class);
		System.out.println(user);
	}
}
