package cl.duoc.vehiculo_service.repository;

import cl.duoc.vehiculo_service.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
