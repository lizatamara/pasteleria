package cl.duoc.despacho_service.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@JsonPropertyOrder({ "id", "nombre", "apellido", "telefono" , "vehiculo"})
@Data
public class ChoferDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private VehiculoDTO vehiculo;

}
