package cl.duoc.chofer_service.mapper;

import cl.duoc.chofer_service.dto.ChoferDTO;
import cl.duoc.chofer_service.model.Chofer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChoferMapper {

    public ChoferDTO toDTO(Chofer chofer) {
        if (chofer == null) return null;

        ChoferDTO dto = new ChoferDTO();
        dto.setId(chofer.getId());
        dto.setNombre(chofer.getNombre());
        dto.setApellido(chofer.getApellido());
        dto.setTelefono(chofer.getTelefono());

        return dto;
    }

    public List<ChoferDTO> toListDTO(List<Chofer> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }


}
