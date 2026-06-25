package com.example.LogiStock_MS_05_Ordenes_Compra.exception;

public class OrdenNoEncontradaException extends RuntimeException{
    public OrdenNoEncontradaException(Long id) {
        super("No se encontró la Orden de Compra con el ID: " + id);
    }

}
