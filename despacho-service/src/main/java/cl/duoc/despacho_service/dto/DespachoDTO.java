package cl.duoc.despacho_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@JsonPropertyOrder({ "id", "estado_despacho", "direccion_entrega", "fecha_estimada" })
@Data
public class DespachoDTO {
    private Long id;
    private String estado_despacho;
    private String direccion_entrega;
    private LocalDate fecha_estimada;
    private ChoferDTO chofer;
}
