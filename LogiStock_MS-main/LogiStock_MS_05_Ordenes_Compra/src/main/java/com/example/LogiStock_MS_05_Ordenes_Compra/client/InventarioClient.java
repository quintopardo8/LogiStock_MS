package com.example.LogiStock_MS_05_Ordenes_Compra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.MovimientoStockRequest;

@FeignClient(name = "logistock-ms-02-inventario")
public interface InventarioClient {

    @PostMapping("/producto/{prodId}/incrementar")
    void incrementarStock(
        @PathVariable("prodId") Long prodId,
        @RequestBody MovimientoStockRequest movimientoRequest
    );

}
