package cl.duoc.evento_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "id", "tipoEvento", "fecha", "cantidad_personas" })
public class EventoDTO {
    private Long id;
    private String tipoEvento;
    private LocalDateTime fecha;
    private int cantidad_personas;
}
