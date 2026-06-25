package com.example.LogiStock_MS_05_Ordenes_Compra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "logistock-ms-01-productos")
public interface ProductoClient {
    
    @GetMapping("/{id}")
    Object obtenerProductoPorId(@PathVariable("id") Long id); 
}
