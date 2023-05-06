package com.morris.stunes;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.controller.AlbumsController;
import com.morris.stunes.controller.ArtistsController;
import com.morris.stunes.controller.PerformanceController;
import com.morris.stunes.controller.TracksController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secrets.properties")
@ComponentScan(excludeFilters={@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value = {
		SpringDataConfiguration.class,
		AlbumsController.class,
		ArtistsController.class,
		PerformanceController.class,
		TracksController.class
})})
/*
 Note: A few controller classes (dependent on data autowires) as well as the SpringDataConfiguration
 is currently being ignored in component scan. This is due to saving cost during development. The
 plan is to implement a local database configuration for development.
 */
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
