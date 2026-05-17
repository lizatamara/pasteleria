package cl.duoc.sucursal_service.repository;

import cl.duoc.sucursal_service.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
