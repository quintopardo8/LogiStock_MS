package com.example.LogiStock_MS_06_Ordenes_Despacho.service;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request.OrdenDespachoRequest;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response.OrdenDespachoResponse;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;

import java.util.List;

public interface IOrdenDespachoService {
    OrdenDespachoResponse crearOrden(OrdenDespachoRequest request);
    OrdenDespachoResponse obtenerPorId(Long id);
    OrdenDespachoResponse obtenerPorSeguimiento(String numeroSeguimiento);
    List<OrdenDespachoResponse> listarTodas();
    List<OrdenDespachoResponse> listarPorEstado(EstadoDespacho estado);
    OrdenDespachoResponse cambiarEstado(Long id, EstadoDespacho nuevoEstado);
}