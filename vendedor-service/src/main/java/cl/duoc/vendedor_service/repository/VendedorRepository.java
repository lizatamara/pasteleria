package cl.duoc.vendedor_service.repository;

import cl.duoc.vendedor_service.model.Vendedor;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    // Reporte 1: Buscar por RUT exacto
    Optional<Vendedor> findByRut(String rut);

    // Reporte 2: Vendedores con sueldo mayor o igual a...
    List<Vendedor> findBySueldoGreaterThanEqual(Integer sueldoMinimo);

    // Reporte 3: Buscar por apellido (ignora mayúsculas y minúsculas)
    List<Vendedor> findByApellidoContainingIgnoreCase(String apellido);
}



