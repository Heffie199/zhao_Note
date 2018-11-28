package com.zhao.eurekaclinet2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //启用服务注册与发现
@EnableFeignClients
public class Eurekaclinet2Application {

	public static void main(String[] args) {
		SpringApplication.run(Eurekaclinet2Application.class, args);
	}
}
