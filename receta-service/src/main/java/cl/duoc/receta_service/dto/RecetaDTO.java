package cl.duoc.receta_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "codigo", "nombre", "descripcion", "precio" , "categoria" })
public class RecetaDTO {
    private Long id;

    private String codigo;

    private String nombre;

    private String descripcion;

    private Integer precio;

    private String categoria;
}
