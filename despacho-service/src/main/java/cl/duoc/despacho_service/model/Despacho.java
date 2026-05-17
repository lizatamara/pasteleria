package cl.duoc.despacho_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El estado del despacho no puede estar vacío")
    private String estado_despacho;
    @NotBlank(message = "La dirección de entrega es obligatoria")
    private String direccion_entrega;
    @NotNull(message = "La fecha no puede estar vacía")
    @FutureOrPresent(message = "Debe ingresar una fecha válida")
    private LocalDate fecha_estimada;
    private Long chofer;

}
