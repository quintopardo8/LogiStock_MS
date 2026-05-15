package com.example.LogiStock_MS_01.exception;

public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(Long id){super("No se ha encontrado categoria con el id: " + id);}
}
