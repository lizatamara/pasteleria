package cl.duoc.administrador_service.reposity;

import cl.duoc.administrador_service.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorResposity extends JpaRepository<Administrador, Long> {

    // Reporte 1: Buscar por RUT exacto
    Optional<Administrador> findByRut(String rut);

    // Reporte 2: Buscar por Email exacto
    Optional<Administrador> findByEmail(String email);

    // Reporte 3: Buscar por apellido aproximado
    List<Administrador> findByApellidoContainingIgnoreCase(String apellido);

}
