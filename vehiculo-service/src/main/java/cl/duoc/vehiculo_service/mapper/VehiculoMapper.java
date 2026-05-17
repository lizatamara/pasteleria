package cl.duoc.vehiculo_service.mapper;

import cl.duoc.vehiculo_service.dto.VehiculoDTO;
import cl.duoc.vehiculo_service.model.Vehiculo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehiculoMapper {

    public VehiculoDTO toDTO(Vehiculo vehiculo) {
        if (vehiculo == null) return null;

        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(vehiculo.getId());
        dto.setPatente(vehiculo.getPatente());
        dto.setCapacidad_kilos(vehiculo.getCapacidad_kilos());

        return dto;
    }


    public List<VehiculoDTO> toListDTO(List<Vehiculo> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
