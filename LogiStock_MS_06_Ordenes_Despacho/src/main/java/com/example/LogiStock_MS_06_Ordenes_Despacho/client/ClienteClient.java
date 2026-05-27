package com.example.LogiStock_MS_06_Ordenes_Despacho.client;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.externa.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LogiStock-MS-04-Cliente", url = "http://localhost:8084/api/clientes")
public interface ClienteClient {

    @GetMapping("/{id}")
    Cliente obtenerClientePorId(@PathVariable("id") Long id);
}