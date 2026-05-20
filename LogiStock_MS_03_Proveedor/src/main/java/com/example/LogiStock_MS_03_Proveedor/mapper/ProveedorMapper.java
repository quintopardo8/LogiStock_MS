package com.example.LogiStock_MS_03_Proveedor.mapper;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest; // Corregido: "Ejemplo" y "Petición"
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse; // Corregido: "Ejemplo" y "respuesta"
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Proveedor toEntity(ProveedorRequest request);

    ProveedorResponse toResponse(Proveedor proveedor);
}