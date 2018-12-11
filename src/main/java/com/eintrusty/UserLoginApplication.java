package com.eintrusty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eintrusty.repo")
public class UserLoginApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(UserLoginApplication.class, args);
	}

}
