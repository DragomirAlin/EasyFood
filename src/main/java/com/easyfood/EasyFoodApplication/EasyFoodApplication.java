package com.easyfood.EasyFoodApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class EasyFoodApplication {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(EasyFoodApplication.class, args);
		logger.info("The application has started.");

	}

}
