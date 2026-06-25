package com.example.LogiStock_MS_01.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {super("No se ha encontrado el producto con el id: " + id);
    }
}