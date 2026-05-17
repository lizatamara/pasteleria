package cl.duoc.despacho_service.repository;

import cl.duoc.despacho_service.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespachoRepository extends JpaRepository<Despacho,Long> {
}
