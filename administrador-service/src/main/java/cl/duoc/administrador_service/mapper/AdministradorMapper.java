package cl.duoc.administrador_service.mapper;

import cl.duoc.administrador_service.dto.AdministradorDTO;
import cl.duoc.administrador_service.model.Administrador;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdministradorMapper {
    public AdministradorDTO toDTO(Administrador administrador) {
        if (administrador == null) return null;

        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(administrador.getId());
        dto.setRut(administrador.getRut());
        dto.setNombre(administrador.getNombre());
        dto.setApellido(administrador.getApellido());
        return dto;
    }

    public List<AdministradorDTO> toListDTO(List<Administrador> listado) {

        return listado.stream()
                .map(this::toDTO)
                .toList();
    }



}
