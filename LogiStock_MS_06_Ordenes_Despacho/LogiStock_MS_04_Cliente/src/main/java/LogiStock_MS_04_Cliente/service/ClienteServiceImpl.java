package LogiStock_MS_04_Cliente.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import LogiStock_MS_04_Cliente.dto.request.ClienteRequest;
import LogiStock_MS_04_Cliente.dto.response.ClienteResponse;
import LogiStock_MS_04_Cliente.exception.ClienteNoEncontradoException;
import LogiStock_MS_04_Cliente.exception.RutDuplicadoException;
import LogiStock_MS_04_Cliente.mapper.ClienteMapper;
import LogiStock_MS_04_Cliente.model.Cliente;
import LogiStock_MS_04_Cliente.model.Estado;
import LogiStock_MS_04_Cliente.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerTodosLosClientes() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    @Override
    @Transactional
    public ClienteResponse guardarCliente(ClienteRequest request) {
        if (clienteRepository.existsByRut(request.getRut())) {
            throw new RutDuplicadoException("El RUT '" + request.getRut() + "' ya se encuentra registrado.");
        }
        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setEstado(Estado.ACTIVO);
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));

        if (!clienteExistente.getRut().equals(request.getRut()) && clienteRepository.existsByRut(request.getRut())) {
            throw new RutDuplicadoException("El RUT '" + request.getRut() + "' ya pertenece a otro cliente.");
        }

        clienteExistente.setNombre(request.getNombre());
        clienteExistente.setRut(request.getRut());
        clienteExistente.setContactoEmail(request.getContactoEmail());
        clienteExistente.setContactoTelefono(request.getContactoTelefono());
        clienteExistente.setDireccion(request.getDireccion()); 

        return clienteMapper.toResponse(clienteRepository.save(clienteExistente));
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));

        clienteExistente.setEstado(Estado.INACTIVO);
        clienteRepository.save(clienteExistente);
    }
}