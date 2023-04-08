package com.morris.stunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secrets.properties")
public class StunesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StunesApplication.class, args);
	}
}
