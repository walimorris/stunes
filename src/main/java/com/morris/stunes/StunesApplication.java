package com.morris.stunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secrets.properties")
public class StunesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StunesApplication.class, args);

		for (String bean : applicationContext.getBeanDefinitionNames()) {
			System.out.println("bean: " + bean);
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StunesApplication.class);
	}
}
