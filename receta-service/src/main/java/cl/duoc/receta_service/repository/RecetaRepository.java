package cl.duoc.receta_service.repository;

import cl.duoc.receta_service.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository  extends JpaRepository<Receta, Long> {

}
