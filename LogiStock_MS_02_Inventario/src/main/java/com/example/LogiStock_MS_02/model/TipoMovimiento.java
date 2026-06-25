package com.example.LogiStock_MS_02.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor             
public enum TipoMovimiento {
    ENTRADA,
    SALIDA,
    AJUSTE_POSITIVO,
    AJUSTE_NEGATIVO
}
