package com.dden.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TodoJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJmsApplication.class, args);
	}

}
