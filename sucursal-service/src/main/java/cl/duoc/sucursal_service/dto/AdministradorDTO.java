package cl.duoc.sucursal_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "rut", "nombre", "apellido" })
public class AdministradorDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
}
