package com.example.LogiStock_MS_06_Ordenes_Despacho.mapper;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request.OrdenDespachoRequest;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response.OrdenDespachoResponse;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.OrdenDespacho;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenDespachoMapper {

    // Ignoramos los campos que se manejan en la base de datos o en el Service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaEnvio", ignore = true)
    OrdenDespacho toEntity(OrdenDespachoRequest request);

    OrdenDespachoResponse toResponse(OrdenDespacho entity);

    List<OrdenDespachoResponse> toResponseList(List<OrdenDespacho> entities);
}