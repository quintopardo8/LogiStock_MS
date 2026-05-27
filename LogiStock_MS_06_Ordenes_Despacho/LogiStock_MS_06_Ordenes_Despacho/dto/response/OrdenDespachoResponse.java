package com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.externa.Cliente;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrdenDespachoResponse {
    private Long id;
    private String numeroSeguimiento;
    private Cliente cliente; 
    private String direccionDespacho;
    private EstadoDespacho estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEnvio;
}