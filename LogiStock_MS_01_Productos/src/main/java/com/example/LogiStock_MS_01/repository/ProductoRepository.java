package com.example.LogiStock_MS_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LogiStock_MS_01.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
