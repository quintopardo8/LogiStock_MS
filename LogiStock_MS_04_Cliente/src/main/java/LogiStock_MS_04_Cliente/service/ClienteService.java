package com.example.LogiStock_MS_04_Cliente.service;

import com.example.LogiStock_MS_04_Cliente.model.Cliente;
import com.example.LogiStock_MS_04_Cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listarTodos() { return repository.findAll(); }
    public Optional<Cliente> buscarPorId(Long id) { return repository.findById(id); }
    public Cliente guardar(Cliente cliente) { return repository.save(cliente); }
    public void eliminar(Long id) { repository.deleteById(id); }
}
