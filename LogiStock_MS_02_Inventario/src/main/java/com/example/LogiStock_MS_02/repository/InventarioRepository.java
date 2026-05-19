package com.example.LogiStock_MS_02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LogiStock_MS_02.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}
