package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void login(String userName, String password) {
		System.out.println("login");
	}
	
	public void aTestLogin(String userName, String password) {
		System.out.println("aTestLogin");
	}

}