package com.example.LogiStock_MS_05_Ordenes_Compra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class LogiStockMs05OrdenesCompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogiStockMs05OrdenesCompraApplication.class, args);
	}

}
