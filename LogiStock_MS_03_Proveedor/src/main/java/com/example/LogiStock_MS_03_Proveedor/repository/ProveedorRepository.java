package com.example.LogiStock_MS_03_Proveedor.repository;

import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}