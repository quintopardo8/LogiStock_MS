package com.example.LogiStock_MS_03_Proveedor.repository;

import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    Optional<Proveedor> findByRut(String rut);

    Optional<Proveedor> findByContactoEmail(String contactoEmail);

    boolean existsByRut(String rut);

    boolean existsByContactoEmail(String contactoEmail);
}