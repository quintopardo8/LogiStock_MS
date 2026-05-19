package LogiStock_MS_04_Cliente.service;

import java.util.List;
import LogiStock_MS_04_Cliente.dto.request.ClienteRequest;
import LogiStock_MS_04_Cliente.dto.response.ClienteResponse;

public interface IClienteService {
    // Sincronizado perfectamente con ClienteServiceImpl y ClienteController
    List<ClienteResponse> obtenerTodosLosClientes();
    ClienteResponse obtenerClientePorId(Long id);
    ClienteResponse guardarCliente(ClienteRequest request);
    ClienteResponse actualizarCliente(Long id, ClienteRequest request);
    void eliminarCliente(Long id);
}