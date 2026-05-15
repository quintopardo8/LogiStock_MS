package com.example.LogiStock_MS_04_Cliente.repository;

import com.example.LogiStock_MS_04_Cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
