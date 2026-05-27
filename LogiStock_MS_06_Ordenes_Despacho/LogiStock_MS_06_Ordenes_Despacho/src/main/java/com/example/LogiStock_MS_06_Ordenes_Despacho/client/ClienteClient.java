package com.example.LogiStock_MS_06_Ordenes_Despacho.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LogiStock-MS-04-Cliente", url = "http://localhost:8084/api/clientes")
public interface ClienteClient {

    @GetMapping("/{id}")
    Object obtenerClientePorId(@PathVariable("id") Long id);
}