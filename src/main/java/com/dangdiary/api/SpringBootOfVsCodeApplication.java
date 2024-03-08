package com.dangdiary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootOfVsCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOfVsCodeApplication.class, args);
	}

}
