package cl.duoc.vendedor_service.repository;

import cl.duoc.vendedor_service.model.Vendedor;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
