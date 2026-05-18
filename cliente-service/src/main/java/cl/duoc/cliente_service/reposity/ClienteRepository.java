package cl.duoc.cliente_service.reposity;

import cl.duoc.cliente_service.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Reporte 1: Buscar por RUT exacto (Devuelve uno solo o vacío)
    Optional<Cliente> findByRut(String rut);

    // Reporte 2: Buscar clientes por comuna (que la dirección contenga el texto)
    List<Cliente> findByDireccionContainingIgnoreCase(String comuna);

    // Reporte 3: Buscar por apellido exacto o aproximado
    List<Cliente> findByApellidoContainingIgnoreCase(String apellido);

    // Reporte 4: Buscar por Email exacto
    Optional<Cliente> findByCorreo(String correo);

    // Reporte 5: Buscar por Nombre aproximado (ignora mayúsculas/minúsculas)
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);


}
