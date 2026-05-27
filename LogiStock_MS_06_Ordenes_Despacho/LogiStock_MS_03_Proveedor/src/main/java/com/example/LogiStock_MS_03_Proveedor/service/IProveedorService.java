package com.example.LogiStock_MS_03_Proveedor.service;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import java.util.List;

public interface IProveedorService {
    ProveedorResponse crearProveedor(ProveedorRequest request);
    List<ProveedorResponse> listarProveedores();
    ProveedorResponse buscarPorId(Long id);
    ProveedorResponse actualizarProveedor(Long id, ProveedorRequest request);
    void eliminarProveedor(Long id);
}