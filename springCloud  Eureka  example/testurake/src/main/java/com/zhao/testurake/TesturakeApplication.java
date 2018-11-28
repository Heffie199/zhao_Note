package com.zhao.testurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TesturakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesturakeApplication.class, args);
	}
}
	