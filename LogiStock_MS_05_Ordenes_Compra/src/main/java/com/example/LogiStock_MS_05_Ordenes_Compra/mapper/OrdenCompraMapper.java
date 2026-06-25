package com.example.LogiStock_MS_05_Ordenes_Compra.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.OrdenCompraRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.response.OrdenCompraResponse;
import com.example.LogiStock_MS_05_Ordenes_Compra.model.OrdenCompra;

@Mapper (componentModel = "spring")
public interface OrdenCompraMapper {

    OrdenCompra toEntity (OrdenCompraRequest ordenCompraRequest);

    OrdenCompraResponse toResponse (OrdenCompra ordenCompra);

    List<OrdenCompraResponse> toResponseList(List<OrdenCompra> ordenes);



}
