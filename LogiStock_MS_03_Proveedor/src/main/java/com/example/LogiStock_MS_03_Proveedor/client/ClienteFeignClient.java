package com.example.LogiStock_MS_03_Proveedor.client;

import com.example.LogiStock_MS_03_Proveedor.dto.ClienteDTO_Externo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente", url = "http://localhost:8084")
public interface ClienteFeignClient {

    @GetMapping("/api/clientes/{id}")
    ClienteDTO_Externo obtenerCliente(@PathVariable("id") Long id);
}
