package cl.duoc.chofer_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@JsonPropertyOrder({ "id", "patente", "capacidad_kilos" })
@Data
public class VehiculoDTO {
    private Long id;
    private String patente;
    private Double capacidad_kilos;
}
