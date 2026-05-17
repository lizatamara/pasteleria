package cl.duoc.sucursal_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({ "id", "codigo", "nombre", "direccion", "telefono" , "administrador", "vendedor"})
@Data
public class SucursalDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String direccion;
    private String telefono;
    private AdministradorDTO administrador;
    private VendedorDTO vendedor;
}
