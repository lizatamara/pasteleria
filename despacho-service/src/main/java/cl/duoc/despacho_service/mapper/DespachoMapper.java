package cl.duoc.despacho_service.mapper;

import cl.duoc.despacho_service.dto.DespachoDTO;
import cl.duoc.despacho_service.model.Despacho;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DespachoMapper {

    public DespachoDTO toDTO(Despacho despacho) {
        if (despacho == null) return null;

        DespachoDTO dto = new DespachoDTO();
        dto.setId(despacho.getId());
        dto.setEstado_despacho(despacho.getEstado_despacho());
        dto.setDireccion_entrega(despacho.getDireccion_entrega());
        dto.setFecha_estimada(despacho.getFecha_estimada());

        return dto;
    }

    public List<DespachoDTO> toListDTO(List<Despacho> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}