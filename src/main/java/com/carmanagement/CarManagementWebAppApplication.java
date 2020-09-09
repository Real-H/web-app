package com.carmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CarManagementWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarManagementWebAppApplication.class, args);
	}

}
