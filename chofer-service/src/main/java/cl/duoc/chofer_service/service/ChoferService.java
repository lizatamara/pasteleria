package cl.duoc.chofer_service.service;

import cl.duoc.chofer_service.clients.VehiculoFeign;
import cl.duoc.chofer_service.dto.ChoferDTO;
import cl.duoc.chofer_service.dto.VehiculoDTO;
import cl.duoc.chofer_service.exception.ChoferLicenciaInvalidaException;
import cl.duoc.chofer_service.mapper.ChoferMapper;
import cl.duoc.chofer_service.model.Chofer;
import cl.duoc.chofer_service.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoferService {
    @Autowired
    private ChoferRepository choferRepository;

    @Autowired
    private ChoferMapper mapper;

    @Autowired
    private VehiculoFeign vehiculoFeign;

    // CAMBIO: Ahora retorna una lista de DTOs con sus vehículos cargados
    public List<ChoferDTO> findAll() {
        List<Chofer> choferes = choferRepository.findAll();

        return choferes.stream().map(chofer -> {
            // 1. Transformamos la entidad a DTO básico
            ChoferDTO choferDTO = mapper.toDTO(chofer);

            // 2. Si el chofer tiene un vehículo asociado, lo buscamos vía Feign
            if (chofer.getVehiculo() != null) {
                try {
                    VehiculoDTO vehiculoDTO = vehiculoFeign.obtenerVehiculo(chofer.getVehiculo());
                    choferDTO.setVehiculo(vehiculoDTO);
                } catch (Exception e) {
                    // Preventivo: si el microservicio de vehículos no responde o falla,
                    // dejamos el vehículo como null para no romper toda la lista.
                    choferDTO.setVehiculo(null);
                }
            }
            return choferDTO;
        }).collect(Collectors.toList());
    }

    // Reporte 1: Por RUT
    public Chofer buscarPorRut(String rut) {
        return choferRepository.findByRut(rut).orElse(null);
    }

    // Reporte 2: Por Licencia
    public List<Chofer> buscarPorLicencia(String licencia) {
        return choferRepository.buscarPorTipoLicencia(licencia);
    }

    // Reporte 3: Por Apellido
    public List<Chofer> buscarPorApellido(String apellido) {
        return choferRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    // Reporte 4: Por Teléfono
    public Chofer buscarPorTelefono(String telefono) {
        return choferRepository.findByTelefono(telefono).orElse(null);
    }

    // Reporte 5: Por ID de Vehículo asignado
    public Chofer buscarPorVehiculo(Long vehiculoId) {
        return choferRepository.findByVehiculo(vehiculoId).orElse(null);
    }







    public ChoferDTO findById(Long id) {
        Chofer chofer = choferRepository.findById(id).orElse(null);
        if (chofer == null) return null;

        ChoferDTO choferDTO = mapper.toDTO(chofer);

        if (chofer.getVehiculo() != null) {
            try {
                VehiculoDTO vehiculoDTO = vehiculoFeign.obtenerVehiculo(chofer.getVehiculo());
                choferDTO.setVehiculo(vehiculoDTO);
            } catch (Exception e) {
                choferDTO.setVehiculo(null);
            }
        }
        return choferDTO;
    }

    public Chofer save(Chofer chofer) {
        return choferRepository.save(chofer);
    }

    public void delete(Long id) {
        choferRepository.deleteById(id);
    }

    public Chofer update(Long id, Chofer chofer) {
        Chofer choferActualizado = choferRepository.findById(id).orElse(null);

        if (choferActualizado == null) return null;

        choferActualizado.setRut(chofer.getRut());
        choferActualizado.setNombre(chofer.getNombre());
        choferActualizado.setApellido(chofer.getApellido());
        choferActualizado.setTipo_licencia(chofer.getTipo_licencia());
        choferActualizado.setTelefono(chofer.getTelefono());
        choferActualizado.setVehiculo(chofer.getVehiculo());

        return choferRepository.save(choferActualizado);
    }


    public Chofer guardarChofer(Chofer chofer) {
        // 1. Validación: Que no venga vacío
        if (chofer.getTipo_licencia() == null || chofer.getTipo_licencia().trim().isEmpty()) {
            throw new ChoferLicenciaInvalidaException("El tipo de licencia es obligatorio para registrar un chofer.");
        }

        // 2. Validación: Solo se permiten licencias profesionales de clase B o A4 para el reparto
        String licencia = chofer.getTipo_licencia().toUpperCase();
        if (!licencia.equals("B") && !licencia.equals("A4")) {
            throw new ChoferLicenciaInvalidaException("Licencia no autorizada. El reparto de pastelería requiere Clase B o A4.");
        }

        return choferRepository.save(chofer);
    }
}
