package com.example.LogiStock_MS_03_Proveedor.mapper;


import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    // Convierte el Request que viene de la web a una Entidad de Base de Datos
    // Ignoramos el id y el estado porque se asignan al guardar en el Service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Proveedor toEntity(ProveedorRequest request);

    // Convierte la Entidad de la Base de Datos al Response limpio para la API
    ProveedorResponse toResponse(Proveedor proveedor);
    
}   