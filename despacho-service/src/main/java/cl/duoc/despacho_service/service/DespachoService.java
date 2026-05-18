package cl.duoc.despacho_service.service;

import cl.duoc.despacho_service.clients.DespachoFeign;
import cl.duoc.despacho_service.dto.ChoferDTO;
import cl.duoc.despacho_service.dto.DespachoDTO;
import cl.duoc.despacho_service.mapper.DespachoMapper;
import cl.duoc.despacho_service.model.Despacho;
import cl.duoc.despacho_service.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespachoService {


    @Autowired
    private DespachoRepository despachoRepository;

    @Autowired
    private DespachoMapper mapper;

    @Autowired
    private DespachoFeign despachoFeign;

    // Retorna la lista de todos los despachos convertidos a DTO
    public List<DespachoDTO> findAll() {
        // 1. Buscamos todos los despachos de la base de datos
        List<Despacho> despachos = despachoRepository.findAll();



        // 2. Recorremos la lista para transformarlos a DTO y cargarles el Chofer vía Feign
        return despachos.stream().map(despacho -> {
            // Transformamos la entidad a DTO básico
            DespachoDTO despachoDTO = mapper.toDTO(despacho);

            // Si el despacho tiene un chofer asignado en la BD, lo vamos a buscar por Feign
            if (despacho.getChofer() != null) {
                try {
                    // Llamamos al microservicio de choferes para traer sus datos
                    ChoferDTO choferDTO = despachoFeign.obtenerChoferes(despacho.getChofer());
                    despachoDTO.setChofer(choferDTO);
                } catch (Exception e) {
                    // Si el microservicio de choferes falla o está apagado,
                    // lo dejamos null para que no se rompa la lista completa.
                    despachoDTO.setChofer(null);
                }
            }
            return despachoDTO;
        }).collect(Collectors.toList());

    }




// ==========================================
    //          Endpoints de Reportes
    // ==========================================

    // Reporte 1: Por Estado
    public List<Despacho> buscarPorEstado(String estado) {
        return despachoRepository.findAll().stream()
                .filter(d -> d.getEstado_despacho() != null && d.getEstado_despacho().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    // Reporte 2: Por Comuna
    public List<Despacho> buscarPorComuna(String comuna) {
        return despachoRepository.findAll().stream()
                .filter(d -> d.getDireccion_entrega() != null && d.getDireccion_entrega().toLowerCase().contains(comuna.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Reporte 3: Por Fecha exacta
    public List<Despacho> buscarPorFecha(java.time.LocalDate fecha) {
        return despachoRepository.findAll().stream()
                .filter(d -> d.getFecha_estimada() != null && d.getFecha_estimada().equals(fecha))
                .collect(Collectors.toList());
    }

    // Reporte 4: Por Fechas futuras
    public List<Despacho> buscarPorFechasFuturas(java.time.LocalDate fecha) {
        return despachoRepository.findAll().stream()
                .filter(d -> d.getFecha_estimada() != null && !d.getFecha_estimada().isBefore(fecha))
                .collect(Collectors.toList());
    }

    // Reporte 5: Por ID de Chofer
    public List<Despacho> buscarPorChofer(Long choferId) {
        return despachoRepository.findAll().stream()
                .filter(d -> d.getChofer() != null && d.getChofer().equals(choferId))
                .collect(Collectors.toList());
    }

    public DespachoDTO findById(Long id) {
        Despacho despacho = despachoRepository.findById(id).orElse(null);
        if (despacho == null) return null;

        DespachoDTO despachoDTO = mapper.toDTO(despacho);

        if (despacho.getChofer() != null) {
            try {
                // Ahora choferDTO es del tipo correcto (ChoferDTO)
                ChoferDTO choferDTO = despachoFeign.obtenerChoferes(despacho.getChofer());
                despachoDTO.setChofer(choferDTO);
            } catch (Exception e) {
                despachoDTO.setChofer(null);
            }
        }
        return despachoDTO;
    }

    // Guarda un nuevo despacho
    public Despacho save(Despacho despacho) {
        return despachoRepository.save(despacho);
    }

    // Elimina un despacho por ID
    public void delete(Long id) {
        despachoRepository.deleteById(id);
    }

    // Actualiza los datos de un despacho existente
    public Despacho update(Long id, Despacho despacho) {
        Despacho despachoActualizado = despachoRepository.findById(id).orElse(null);

        if (despachoActualizado == null) return null;

        // Actualizamos los campos específicos de tu modelo Despacho
        despachoActualizado.setEstado_despacho(despacho.getEstado_despacho());
        despachoActualizado.setDireccion_entrega(despacho.getDireccion_entrega());
        despachoActualizado.setFecha_estimada(despacho.getFecha_estimada());

        return despachoRepository.save(despachoActualizado);
    }
}
