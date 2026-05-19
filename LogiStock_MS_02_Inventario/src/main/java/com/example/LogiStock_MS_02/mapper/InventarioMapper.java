package com.example.LogiStock_MS_02.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.LogiStock_MS_02.dto.InventarioRequest;
import com.example.LogiStock_MS_02.dto.InventarioResponse;
import com.example.LogiStock_MS_02.model.Inventario;

@Mapper(componentModel = "spring")
public interface InventarioMapper {

    Inventario toEntity(InventarioRequest inventarioRequest);
    
    @Mapping(target = "stockEfectivo", expression = "java(inventario.getCantidadDisponible() - inventario.getCantidadReservada())")
    InventarioResponse toResponse (Inventario inventario);
    // (Cant Disp - Cant Reservada) se mapea como atributo
    // stockEfectivo en InventarioResponse =) 

    List<InventarioResponse> toResponseList (List<Inventario> inventarioList);

}
