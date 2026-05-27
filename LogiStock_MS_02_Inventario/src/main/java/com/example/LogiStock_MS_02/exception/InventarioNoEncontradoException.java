package com.example.LogiStock_MS_02.exception;

public class InventarioNoEncontradoException extends RuntimeException{
    public InventarioNoEncontradoException(String message){
        super(message);
    }

    //"En blanco" para que en capa service pueda citar
    //manualmente InventarioID o ProductoID en el mensaje de la excepción.

}
