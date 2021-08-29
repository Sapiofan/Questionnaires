package com.sapiofan.surveys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories("com.sapiofan.surveys.repository")
public class SurveysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveysApplication.class, args);
	}

}
