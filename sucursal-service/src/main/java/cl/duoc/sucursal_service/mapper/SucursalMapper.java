package cl.duoc.sucursal_service.mapper;

import cl.duoc.sucursal_service.dto.SucursalDTO;
import cl.duoc.sucursal_service.model.Sucursal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SucursalMapper {

    // Convierte una sola entidad Sucursal a su DTO básico
    public SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) return null;

        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setCodigo(sucursal.getCodigo());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setTelefono(sucursal.getTelefono());

        return dto;
    }

    // Convierte la lista completa de Sucursales a una lista de DTOs
    public List<SucursalDTO> toListDTO(List<Sucursal> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}