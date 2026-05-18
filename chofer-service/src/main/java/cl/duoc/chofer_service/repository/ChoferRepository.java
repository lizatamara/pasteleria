package cl.duoc.chofer_service.repository;

import cl.duoc.chofer_service.model.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChoferRepository extends JpaRepository<Chofer, Long> {

    // Reporte 1: Buscar por RUT exacto
    Optional<Chofer> findByRut(String rut);

    @Query("SELECT c FROM Chofer c WHERE c.tipo_licencia = :licencia")
    List<Chofer> buscarPorTipoLicencia(@Param("licencia") String licencia);

    // Reporte 3: Buscar por apellido (ignora mayúsculas/minúsculas)
    List<Chofer> findByApellidoContainingIgnoreCase(String apellido);

    // Reporte 4: Buscar por teléfono exacto
    Optional<Chofer> findByTelefono(String telefono);

    // Reporte 5: Buscar qué chofer tiene asignado un vehículo específico por su ID remoto
    Optional<Chofer> findByVehiculo(Long vehiculoId);
}
