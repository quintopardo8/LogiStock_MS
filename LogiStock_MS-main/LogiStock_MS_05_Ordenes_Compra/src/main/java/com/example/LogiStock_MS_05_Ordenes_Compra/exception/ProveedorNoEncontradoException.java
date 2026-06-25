package com.example.LogiStock_MS_05_Ordenes_Compra.exception;

public class ProveedorNoEncontradoException extends RuntimeException{

    public ProveedorNoEncontradoException (Long id) {
        super("No se encontró el proveedor con ID: " + id);
    }

}
