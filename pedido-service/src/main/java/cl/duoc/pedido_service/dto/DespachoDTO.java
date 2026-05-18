package cl.duoc.pedido_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
@JsonPropertyOrder({ "id", "estado_despacho", "direccion_entrege", "fecha_estimada" }) // <-- ¡Agregado "chofer" aquí al final!
@Data
public class DespachoDTO {
    private Long id;

    @JsonProperty("estado_despacho")
    private String estadoDespacho;

    @JsonProperty("direccion_entrege")
    private String direccionEntrege;

    @JsonProperty("fecha_estimada")
    private String fechaEstimada; // String evita que Feign se caiga por la fecha




}
