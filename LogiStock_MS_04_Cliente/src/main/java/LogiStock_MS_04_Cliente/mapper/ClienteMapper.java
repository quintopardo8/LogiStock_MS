package LogiStock_MS_04_Cliente.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import LogiStock_MS_04_Cliente.dto.request.ClienteRequest;
import LogiStock_MS_04_Cliente.dto.response.ClienteResponse;
import LogiStock_MS_04_Cliente.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "ACTIVO")
    Cliente toEntity(ClienteRequest request);

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true) 
    void updateEntityFromRequest(ClienteRequest request, @MappingTarget Cliente entity);
}