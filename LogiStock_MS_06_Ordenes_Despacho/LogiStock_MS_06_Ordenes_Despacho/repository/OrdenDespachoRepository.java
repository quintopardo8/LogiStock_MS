package com.example.LogiStock_MS_06_Ordenes_Despacho.repository;

import com.example.LogiStock_MS_06_Ordenes_Despacho.model.OrdenDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenDespachoRepository extends JpaRepository<OrdenDespacho, Long> {
    
    Optional<OrdenDespacho> findByNumeroSeguimiento(String numeroSeguimiento);
    
    List<OrdenDespacho> findByEstado(EstadoDespacho estado);
}