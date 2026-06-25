package com.example.LogiStock_MS_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LogiStockMs01Application {

	public static void main(String[] args) {
		SpringApplication.run(LogiStockMs01Application.class, args);
	}

}
