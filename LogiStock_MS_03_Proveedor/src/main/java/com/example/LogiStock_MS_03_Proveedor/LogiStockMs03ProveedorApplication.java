package com.example.LogiStock_MS_03_Proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LogiStockMs03ProveedorApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogiStockMs03ProveedorApplication.class, args);
	}
}