package cl.duoc.vehiculo_service.service;

import cl.duoc.vehiculo_service.dto.VehiculoDTO;
import cl.duoc.vehiculo_service.exception.VehiculoCapacidadInvalidaException;
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

    // AQUÍ VA EL METODO QUE ME MOSTRASTE:
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        // 1. Validación de negocio: No puede ser menor o igual a 0 kilos
        if (vehiculo.getCapacidad_kilos() == null || vehiculo.getCapacidad_kilos() <= 0) {
            throw new VehiculoCapacidadInvalidaException("La capacidad del vehículo debe ser mayor a 0 kilos.");
        }

        // 2. Validación de negocio: No permite camiones gigantes en la pastelería
        if (vehiculo.getCapacidad_kilos() > 3500.0) {
            throw new VehiculoCapacidadInvalidaException("No se permiten vehículos de alto tonelaje (máx 3500 kg) para el reparto de pastelería.");
        }

        // 3. Si pasa las dos alertas de arriba, recién guarda en la Base de Datos
        return vehiculoRepository.save(vehiculo);
    }




    // Reporte 1: Por Patente
    public Vehiculo buscarPorPatente(String patente) {
        return vehiculoRepository.findByPatente(patente).orElse(null);
    }

    // Reporte 2: Por Marca
    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    // Reporte 5: Por Modelo
    public List<Vehiculo> buscarPorModelo(String modelo) {
        return vehiculoRepository.findByModeloContainingIgnoreCase(modelo);
    }


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
