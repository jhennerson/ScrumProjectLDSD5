package com.ifsp.scrumProjectLDSD5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.validation.annotation.Validated;

@Validated
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ScrumSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrumSpringApplication.class, args);
	}

}
