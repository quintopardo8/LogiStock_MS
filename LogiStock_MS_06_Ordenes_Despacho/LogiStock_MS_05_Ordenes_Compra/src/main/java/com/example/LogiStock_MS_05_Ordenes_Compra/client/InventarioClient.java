package com.example.LogiStock_MS_05_Ordenes_Compra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.MovimientoStockRequest;

@FeignClient(name = "LogiStock-MS-02-Inventario", url = "http://localhost:8082/api/v1/inventarios")
public interface InventarioClient {

    @PostMapping("/producto/{prodId}/incrementar")
    void incrementarStock(
        @PathVariable("prodId") Long prodId,
        @RequestBody MovimientoStockRequest movimientoRequest
    );

}
