package com.example.LogiStock_MS_06_Ordenes_Despacho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LogiStockMs06OrdenesDespachoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogiStockMs06OrdenesDespachoApplication.class, args);
    }
}