package cl.duoc.evento_service.mapper;

import cl.duoc.evento_service.dto.EventoDTO;
import cl.duoc.evento_service.model.Evento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventoMapper {
    public EventoDTO toDTO(Evento evento) {
        if (evento == null) return null;

        EventoDTO dto = new EventoDTO();
        dto.setTipoEvento(evento.getTipoEvento());
        dto.setFecha(evento.getFecha());
        dto.setCantidad_personas(evento.getCantidad_personas());

        return dto;
    }

    public List<EventoDTO> toListDTO(List<Evento> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }




}
