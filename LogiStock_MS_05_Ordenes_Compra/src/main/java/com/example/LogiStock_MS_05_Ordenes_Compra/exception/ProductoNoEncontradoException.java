package com.example.LogiStock_MS_05_Ordenes_Compra.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    
    public ProductoNoEncontradoException(Long id) {
        super("No se encontró el producto con ID: " + id);
    }

}
