package cl.duoc.evento_service.repository;

import cl.duoc.evento_service.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Reporte 1: Filtrar por tipo (Fíjate en la T mayúscula de TipoEvento)
    List<Evento> findByTipoEventoContainingIgnoreCase(String tipoEvento);

    @Query("SELECT e FROM Evento e WHERE e.cantidad_personas >= :cantidad")
    List<Evento> buscarPorCantidadPersonas(@Param("cantidad") Integer cantidad);
    // Reporte 3: Cambiado a LocalDateTime para que calce con tu BD
    List<Evento> findByFecha(LocalDateTime fecha);
}
