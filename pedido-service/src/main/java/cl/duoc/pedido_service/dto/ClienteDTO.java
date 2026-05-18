package cl.duoc.pedido_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "rut", "nombre", "apellido" })
public class ClienteDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;

}
