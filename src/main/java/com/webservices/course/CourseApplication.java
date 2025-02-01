package com.webservices.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotação que indica que é uma aplicação Spring Boot (porta de entrada)
public class CourseApplication { // Classe principal, inicializa todo o projeto 

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
