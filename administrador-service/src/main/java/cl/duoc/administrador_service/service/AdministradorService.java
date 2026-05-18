package cl.duoc.administrador_service.service;

import cl.duoc.administrador_service.dto.AdministradorDTO;
import cl.duoc.administrador_service.mapper.AdministradorMapper;
import cl.duoc.administrador_service.model.Administrador;
import cl.duoc.administrador_service.reposity.AdministradorResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorResposity administradorResposity;

    @Autowired
    private AdministradorMapper mapper;

    // Trae todos los administradores de la base de datos
    public List<Administrador> findAll() {
        return administradorResposity.findAll();
    }

    // Reporte 1: Por RUT
    public Administrador buscarPorRut(String rut) {
        return administradorResposity.findByRut(rut).orElse(null);
    }

    // Reporte 2: Por Email
    public Administrador buscarPorEmail(String email) {
        return administradorResposity.findByEmail(email).orElse(null);
    }

    // Reporte 3: Por Apellido
    public List<Administrador> buscarPorApellido(String apellido) {
        return administradorResposity.findByApellidoContainingIgnoreCase(apellido);
    }





    // Busca un administrador por ID y lo transforma a DTO
    public AdministradorDTO findById(Long id) {
        Administrador administrador = administradorResposity.findById(id).orElse(null);
        return mapper.toDTO(administrador);
    }

    // Guarda un nuevo administrador
    public Administrador save(Administrador administrador) {
        return administradorResposity.save(administrador);
    }

    // Borra un administrador por su ID
    public void delete(Long id) {
        administradorResposity.deleteById(id);
    }

    // Actualiza los datos de un administrador existente
    public Administrador update(Long id, Administrador administrador) {
        Administrador adminExistente = administradorResposity.findById(id).orElse(null);

        if (adminExistente == null) return null;

        // Actualizamos los campos según el modelo de la imagen
        adminExistente.setRut(administrador.getRut());
        adminExistente.setNombre(administrador.getNombre());
        adminExistente.setApellido(administrador.getApellido());
        adminExistente.setEmail(administrador.getEmail());
        adminExistente.setPassword(administrador.getPassword());

        return administradorResposity.save(adminExistente);
    }

}