package cl.duoc.chofer_service.service;


import cl.duoc.chofer_service.clients.VehiculoFeign;
import cl.duoc.chofer_service.dto.ChoferDTO;
import cl.duoc.chofer_service.dto.VehiculoDTO;
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
            if (chofer.getVehiculoId() != null) {
                try {
                    VehiculoDTO vehiculoDTO = vehiculoFeign.obtenerVehiculo(chofer.getVehiculoId());
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

    public ChoferDTO findById(Long id) {
        Chofer chofer = choferRepository.findById(id).orElse(null);
        if (chofer == null) return null;

        ChoferDTO choferDTO = mapper.toDTO(chofer);

        if (chofer.getVehiculoId() != null) {
            try {
                VehiculoDTO vehiculoDTO = vehiculoFeign.obtenerVehiculo(chofer.getVehiculoId());
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
        choferActualizado.setVehiculoId(chofer.getVehiculoId());

        return choferRepository.save(choferActualizado);
    }


}
