package LogiStock_MS_04_Cliente.controller;

import LogiStock_MS_04_Cliente.dto.ClienteDTO;
import LogiStock_MS_04_Cliente.model.Cliente;
import LogiStock_MS_04_Cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

private ClienteDTO mapiarADto(Cliente c) {
        return new ClienteDTO(
            c.getId(), 
            c.getNombre() + " " + c.getApellido(), 
            c.getEmail(), 
            c.getTelefono() 
        );
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos().stream()
                .map(this::mapiarADto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(mapiarADto(service.guardar(cliente)));
    }
}