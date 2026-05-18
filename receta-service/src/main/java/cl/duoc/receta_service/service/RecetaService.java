package cl.duoc.receta_service.service;

import cl.duoc.receta_service.dto.RecetaDTO;
import cl.duoc.receta_service.exception.RecetaCodigoInvalidoException;
import cl.duoc.receta_service.mapper.RecetaMapper;
import cl.duoc.receta_service.model.Receta;
import cl.duoc.receta_service.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RecetaMapper mapper;

    // Trae todas las recetas de la BD
    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    // Reporte 1: Por categoría
    public List<Receta> buscarPorCategoria(String categoria) {
        return recetaRepository.findByCategoria(categoria);
    }

    // Reporte 2: Por precio máximo
    public List<Receta> buscarPorPrecioMaximo(Integer precio) {
        return recetaRepository.findByPrecioLessThanEqual(precio);
    }

    // Reporte 3: Por palabra clave en el nombre
    public List<Receta> buscarPorNombre(String palabra) {
        return recetaRepository.findByNombreContainingIgnoreCase(palabra);
    }



    // Busca una receta por ID y la transforma a DTO
    public RecetaDTO findById(Long id) {
        Receta receta = recetaRepository.findById(id).orElse(null);
        return mapper.toDTO(receta);
    }


    // Guarda una nueva receta o actualiza una existente
    public Receta save(Receta receta) {
        return recetaRepository.save(receta);
    }

    // Borra una receta por su ID
    public void delete(Long id) {
        recetaRepository.deleteById(id);
    }



    public Receta update(Long id, Receta receta) {
        Receta recetaActualizada = recetaRepository.findById(id).orElse(null);

        if (recetaActualizada == null) return null;
        recetaActualizada.setNombre(receta.getNombre());
        recetaActualizada.setDescripcion(receta.getDescripcion());
        recetaActualizada.setPrecio(receta.getPrecio());
        return recetaRepository.save(recetaActualizada);
    }

    public Receta guardarReceta(Receta receta) {
        // 1. Validación básica: Que el precio no sea nulo o negativo
        if (receta.getPrecio() == null || receta.getPrecio() <= 0) {
            throw new RecetaCodigoInvalidoException("El precio de la receta debe ser un valor mayor a $0.");
        }

        // 2. NUEVA REGLA DE NEGOCIO: Ningún producto artesanal se vende a menos de $2.000 pesos
        if (receta.getPrecio() < 2000) {
            throw new RecetaCodigoInvalidoException("Precio bajo el costo mínimo. El valor base de cualquier receta de producción debe ser mínimo $2.000.");
        }

        return recetaRepository.save(receta);
    }
    // Tus otros métodos (findAll, etc.) siguen abajo igual...
}
