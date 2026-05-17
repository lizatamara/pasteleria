package cl.duoc.vehiculo_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "patente", "capacidad_kilos" })
public class VehiculoDTO {
    private Long id;
    private String patente;
    private Double capacidad_kilos;
}
