package cl.duoc.receta_service.mapper;

import cl.duoc.receta_service.dto.RecetaDTO;
import cl.duoc.receta_service.model.Receta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecetaMapper {

    public RecetaDTO toDTO(Receta receta){

    if (receta == null) return null;

    RecetaDTO dto = new RecetaDTO();

        dto.setId(receta.getId());
        dto.setCodigo(receta.getCodigo());
        dto.setNombre(receta.getNombre());
        dto.setDescripcion(receta.getDescripcion());
        dto.setPrecio(receta.getPrecio());
        dto.setCategoria(receta.getCategoria());




    return dto;
    }


    public List<RecetaDTO> toListDTO(List<Receta> listado) {

        return listado.stream()
                .map(this::toDTO)
                .toList();
    }


}
