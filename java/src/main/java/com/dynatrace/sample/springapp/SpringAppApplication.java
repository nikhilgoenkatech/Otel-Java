package com.dynatrace.sample.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dynatrace.sample")
public class SpringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}
}
