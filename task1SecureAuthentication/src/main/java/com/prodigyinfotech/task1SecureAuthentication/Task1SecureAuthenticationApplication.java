package com.prodigyinfotech.task1SecureAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
@Async
public class Task1SecureAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1SecureAuthenticationApplication.class, args);
	}

}
