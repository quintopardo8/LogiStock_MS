package com.example.LogiStock_MS_03_Proveedor;

import com.example.LogiStock_MS_03_Proveedor.controller.ProveedorController;
import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.service.IProveedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProveedorService service;

    @Autowired
    private ObjectMapper objectMapper;

    private ProveedorResponse proveedorSample;

    @BeforeEach
    void setUp() {
        proveedorSample = new ProveedorResponse();
        proveedorSample.setId(1L);
        proveedorSample.setRut("12345678-9");
        proveedorSample.setNombre("Distribuidora LogiStock Chile");
        proveedorSample.setContactoTelefono("+56912345678");
        proveedorSample.setContactoEmail("contacto@logistock.cl");
        proveedorSample.setDireccion("Av. Vitacura 1234, Santiago");
        proveedorSample.setEstado("ACTIVO");
    }

    // 1. Test Listar todo (HTTP 200)
    @Test
    void testListarProveedores_Exito() throws Exception {
        List<ProveedorResponse> lista = Arrays.asList(proveedorSample);
        when(service.listarProveedores()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/proveedores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Distribuidora LogiStock Chile"));
    }

    // 2. Test Buscar por ID Existente (HTTP 200)
    @Test
    void testBuscarPorId_Exito() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(proveedorSample);

        mockMvc.perform(get("/api/v1/proveedores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("12345678-9"));
    }

    // 3. Test Crear Proveedor (HTTP 201)
    @Test
    void testCrearProveedor_Exito() throws Exception {
        ProveedorRequest request = new ProveedorRequest(
            "12345678-9", 
            "Distribuidora LogiStock Chile", 
            "+56912345678", 
            "contacto@logistock.cl", 
            "Av. Vitacura 1234, Santiago"
        );

        when(service.crearProveedor(any(ProveedorRequest.class))).thenReturn(proveedorSample);

        mockMvc.perform(post("/api/v1/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 4. Test Eliminar Proveedor (HTTP 204)
    @Test
    void testEliminarProveedor_Exito() throws Exception {
        doNothing().when(service).eliminarProveedor(1L);

        mockMvc.perform(delete("/api/v1/proveedores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // 5. Test Modificar/Actualizar Proveedor (HTTP 200)
    @Test
    void testActualizarProveedor_Exito() throws Exception {
        ProveedorRequest request = new ProveedorRequest(
            "12345678-9", 
            "Nombre Modificado", 
            "+56912345678", 
            "contacto@logistock.cl", 
            "Av. Vitacura 1234, Santiago"
        );

        when(service.actualizarProveedor(eq(1L), any(ProveedorRequest.class))).thenReturn(proveedorSample);

        mockMvc.perform(put("/api/v1/proveedores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}