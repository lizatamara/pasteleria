package cl.duoc.vendedor_service.mapper;

import cl.duoc.vendedor_service.dto.VendedorDTO;
import cl.duoc.vendedor_service.model.Vendedor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendedorMapper {
    public VendedorDTO toDTO(Vendedor vendedor) {
        if (vendedor == null) return null;

        VendedorDTO dto = new VendedorDTO();
        dto.setId(vendedor.getId());
        dto.setRut(vendedor.getRut());
        dto.setNombre(vendedor.getNombre());
        dto.setApellido(vendedor.getApellido());

        return dto;
    }


    public List<VendedorDTO> toListDTO(List<Vendedor> listado) {
        if (listado == null) return null;

        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
