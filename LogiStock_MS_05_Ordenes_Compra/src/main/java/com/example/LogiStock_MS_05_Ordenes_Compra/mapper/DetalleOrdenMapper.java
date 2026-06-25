package com.example.LogiStock_MS_05_Ordenes_Compra.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.DetalleOrdenRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.response.DetalleOrdenResponse;
import com.example.LogiStock_MS_05_Ordenes_Compra.model.DetalleOrden;

@Mapper (componentModel = "spring")
public interface DetalleOrdenMapper {

    DetalleOrden toEntity(DetalleOrdenRequest detalleOrdenRequest);
    DetalleOrdenResponse toResponse(DetalleOrden detalleOrden);
    List<DetalleOrdenResponse> toResponseList(List<DetalleOrden> detalleOrdenesLista);


}
