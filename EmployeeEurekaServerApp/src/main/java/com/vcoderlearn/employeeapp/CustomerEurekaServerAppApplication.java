package com.vcoderlearn.employeeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CustomerEurekaServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerEurekaServerAppApplication.class, args);
	}

}
