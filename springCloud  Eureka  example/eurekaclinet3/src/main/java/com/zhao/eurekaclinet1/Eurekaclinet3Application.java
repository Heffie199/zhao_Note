package com.zhao.eurekaclinet1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Eurekaclinet3Application {

	public static void main(String[] args) {
		SpringApplication.run(Eurekaclinet3Application.class, args);
	}
}
