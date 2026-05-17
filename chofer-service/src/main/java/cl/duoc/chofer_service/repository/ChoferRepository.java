package cl.duoc.chofer_service.repository;

import cl.duoc.chofer_service.model.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoferRepository extends JpaRepository<Chofer, Long> {
}
