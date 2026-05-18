package cl.duoc.receta_service.repository;

import cl.duoc.receta_service.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository  extends JpaRepository<Receta, Long> {

    // Reporte 1: Filtrar por categoría
    List<Receta> findByCategoria(String categoria);

    // Reporte 2: Recetas con precio menor o igual a...
    List<Receta> findByPrecioLessThanEqual(Integer precioMaximo);

    // Reporte 3: Buscar por nombre que contenga un texto
    List<Receta> findByNombreContainingIgnoreCase(String nombre);

}
