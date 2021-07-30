package com.pomoguy.MonArch;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication
@SpringBootApplication
public class MonArchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonArchApplication.class, args);
	}

}
