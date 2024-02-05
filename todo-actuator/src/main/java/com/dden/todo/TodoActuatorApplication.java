package com.dden.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TodoActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoActuatorApplication.class, args);
	}

}
