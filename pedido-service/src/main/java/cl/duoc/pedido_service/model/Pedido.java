package cl.duoc.pedido_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer monto_total;
    private LocalDate fecha_creacion;
    private LocalTime hora_creacion;
    private String estado_pedido;
    private String metodo_pago;

    // Los IDs lógicos al final de la fila
    private Long sucursal;
    private Long cliente;
    private Long despacho;
    private Long receta;
    private Long evento;
}
