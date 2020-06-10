package com.vcoderlearn.customerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EmployeeZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeZuulApiGatewayApplication.class, args);
	}

}
