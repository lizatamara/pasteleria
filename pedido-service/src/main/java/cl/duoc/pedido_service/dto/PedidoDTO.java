package cl.duoc.pedido_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder({ "id", "monto_total", "fecha_creacion", "hora_creacion", "estado_pedido", "metodo_pago", "sucursal", "cliente", "despacho", "receta", "evento" })
@Data
public class PedidoDTO {

        private Long id;
        private Integer monto_total;
        private LocalDate fecha_creacion;
        private LocalTime hora_creacion;
        private String estado_pedido;
        private String metodo_pago;

        // Aquí están todos los externos que llamamos por Feign
        private SucursalDTO sucursal;
        private ClienteDTO cliente;
        private DespachoDTO despacho;
        private RecetaDTO receta;
        private EventoDTO evento;
}
