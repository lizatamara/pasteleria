package cl.duoc.cliente_service.mapper;

import cl.duoc.cliente_service.dto.ClienteDTO;
import cl.duoc.cliente_service.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
public class ClienteMapper {
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;

        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setRut(cliente.getRut());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        return dto;
    }



    public List<ClienteDTO> toListDTO(List<Cliente> listado) {
        if (listado == null) return null;

        return listado.stream()
                .map(this::toDTO)
                .toList();
    }


}
