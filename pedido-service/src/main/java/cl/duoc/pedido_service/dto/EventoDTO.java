package cl.duoc.pedido_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "id", "tipoEvento", "fecha", "cantidad_personas" })
public class EventoDTO {
    @JsonProperty("id")
    private Long id;
    private String tipoEvento;
    private LocalDateTime fecha;
    @JsonProperty("cantidad_personas")
    private int cantidad_personas;
}
