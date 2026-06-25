package com.example.LogiStock_MS_01.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto toEntity(ProductoRequest productoRequest);
    ProductoResponse toResponse(Producto producto);
    List<ProductoResponse> toResponseList(List<Producto> productoList);
}
