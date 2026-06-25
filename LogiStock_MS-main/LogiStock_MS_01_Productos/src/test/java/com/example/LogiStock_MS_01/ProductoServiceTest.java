package com.example.LogiStock_MS_01;

import com.example.LogiStock_MS_01.dto.request.ActualizarEstadoRequest;
import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.exception.ProductoNoEncontradoException;
import com.example.LogiStock_MS_01.mapper.ProductoMapper;
import com.example.LogiStock_MS_01.model.Estado;
import com.example.LogiStock_MS_01.model.Producto;
import com.example.LogiStock_MS_01.repository.ProductoRepository;
import com.example.LogiStock_MS_01.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock 
    private ProductoRepository productoRepository;

    @Mock 
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoService productoService;

    private Producto crearProducto (Long id, String sku, String nombre, Estado estado){
        Producto testProd = new Producto();
        testProd.setId(id);
        testProd.setSku(sku);
        testProd.setNombre(nombre);
        testProd.setDescripcion("Descripcion Test");
        testProd.setPrecio(11000);
        testProd.setEstado(estado);
        return testProd;
    }

    private ProductoResponse crearProductoResponse (Long id, String nombre, Estado estado){
        ProductoResponse testResponse = new ProductoResponse();
        testResponse.setId(id);
        testResponse.setNombre(nombre);
        testResponse.setEstado(estado);
        return testResponse;
        
    }

    @Test
    @DisplayName("Debe retornar Producto cuando ID existe")
    void debeRetornarProductoCuandoIdExiste(){
        //Given

        Producto producto = crearProducto (1L, "SKU-001", "Teclado Mecanico", Estado.ACTIVO);
        ProductoResponse response = crearProductoResponse (1L, "Teclado Mecanico", Estado.ACTIVO);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoMapper.toResponse(producto)).thenReturn(response);

        //When
        ProductoResponse resultado = productoService.obtenerPorId(1L);

        //Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Teclado Mecanico", resultado.getNombre());

    }





}
