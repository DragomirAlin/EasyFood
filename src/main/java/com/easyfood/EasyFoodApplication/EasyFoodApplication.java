package com.easyfood.EasyFoodApplication;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
public class EasyFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyFoodApplication.class, args);
		log.info("The application has started.");

	}

}
