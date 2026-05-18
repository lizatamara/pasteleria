package cl.duoc.vehiculo_service.repository;

import cl.duoc.vehiculo_service.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Reporte 1: Buscar por patente exacta
    Optional<Vehiculo> findByPatente(String patente);

    // Reporte 2: Buscar por marca (ignora mayúsculas/minúsculas)
    List<Vehiculo> findByMarcaContainingIgnoreCase(String marca);
    // Reporte 5: Buscar por modelo (ignora mayúsculas/minúsculas)
    List<Vehiculo> findByModeloContainingIgnoreCase(String modelo);

}
