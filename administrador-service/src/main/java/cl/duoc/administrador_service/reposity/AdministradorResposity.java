package cl.duoc.administrador_service.reposity;

import cl.duoc.administrador_service.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorResposity extends JpaRepository<Administrador, Long> {

}
