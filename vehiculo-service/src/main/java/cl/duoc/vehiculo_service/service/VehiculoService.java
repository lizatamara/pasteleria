package cl.duoc.vehiculo_service.service;

import cl.duoc.vehiculo_service.dto.VehiculoDTO;
import cl.duoc.vehiculo_service.mapper.VehiculoMapper;
import cl.duoc.vehiculo_service.model.Vehiculo;
import cl.duoc.vehiculo_service.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoMapper mapper;

    // Trae todos los vehículos de la base de datos
    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    // Busca un vehículo por ID y lo transforma a DTO
    public VehiculoDTO findById(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        return mapper.toDTO(vehiculo);
    }


    // Guarda un nuevo vehículo
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    // Borra un vehículo por su ID
    public void delete(Long id) {
        vehiculoRepository.deleteById(id);
    }

    // Actualiza los datos de un vehículo existente
    public Vehiculo update(Long id, Vehiculo vehiculo) {
        Vehiculo vehiculoActualizado = vehiculoRepository.findById(id).orElse(null);

        if (vehiculoActualizado == null) return null;

        // Actualizamos los campos según tu modelo
        vehiculoActualizado.setPatente(vehiculo.getPatente());
        vehiculoActualizado.setCapacidad_kilos(vehiculo.getCapacidad_kilos());
        vehiculoActualizado.setMarca(vehiculo.getMarca());
        vehiculoActualizado.setModelo(vehiculo.getModelo());

        return vehiculoRepository.save(vehiculoActualizado);
    }
}
