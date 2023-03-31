package com.imranmadbar;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
public class SpringSecurityMySqlApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(SpringSecurityMySqlApplication.class, args);
		System.out.println("Spring Security MVC Application Run Successfully Done !");	
		System.out.println();
		System.out.println("Default User");
		System.out.println("-----------------------");
		System.out.println("Username : imranmadbar");
		System.out.println("Password : imranmadbar");
		System.out.println("http://localhost:8181/");
		System.out.println("Using this credential you can log in to the app as a Super admin and able to create users, assign their roles.");
	}
	
	
}
