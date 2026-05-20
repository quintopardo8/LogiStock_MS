package com.example.LogiStock_MS_05_Ordenes_Compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LogiStock_MS_05_Ordenes_Compra.model.DetalleOrden;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long>{

}
