package cl.duoc.receta_service.service;

import cl.duoc.receta_service.dto.RecetaDTO;
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

}
