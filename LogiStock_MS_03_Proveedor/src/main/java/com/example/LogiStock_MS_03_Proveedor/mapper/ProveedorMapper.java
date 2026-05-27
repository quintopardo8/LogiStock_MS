package com.example.LogiStock_MS_03_Proveedor.mapper;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Proveedor toEntity(ProveedorRequest request);

    ProveedorResponse toResponse(Proveedor proveedor);

    List<ProveedorResponse> toResponseList(List<Proveedor> proveedores);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntityFromRequest(ProveedorRequest request, @MappingTarget Proveedor proveedorExistente);
}