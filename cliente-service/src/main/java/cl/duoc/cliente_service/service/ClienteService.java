package cl.duoc.cliente_service.service;

import cl.duoc.cliente_service.dto.ClienteDTO;
import cl.duoc.cliente_service.exception.ClienteCorreoInvalidoException;
import cl.duoc.cliente_service.mapper.ClienteMapper;
import cl.duoc.cliente_service.model.Cliente;
import cl.duoc.cliente_service.reposity.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;

    // Trae todos los clientes de la base de datos
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    // Reporte 1: Buscar por RUT
    public Cliente buscarPorRut(String rut) {
        return clienteRepository.findByRut(rut).orElse(null);
    }

    // Reporte 2: Buscar por Comuna/Dirección
    public List<Cliente> buscarPorComuna(String comuna) {
        return clienteRepository.findByDireccionContainingIgnoreCase(comuna);
    }

    // Reporte 3: Buscar por Apellido
    public List<Cliente> buscarPorApellido(String apellido) {
        return clienteRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    // Reporte 4: Buscar por Email
    public Cliente buscarPorEmail(String correo) {
        return clienteRepository.findByCorreo(correo).orElse(null);
    }

    // Reporte 5: Buscar por Nombre
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }









    // Busca un cliente por ID y lo transforma a DTO
    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return mapper.toDTO(cliente);
    }

    // Guarda un nuevo cliente
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Borra un cliente por su ID
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    // Actualiza los datos de un cliente existente
    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteActualizado = clienteRepository.findById(id).orElse(null);

        if (clienteActualizado == null) return null;

        // Actualizamos todos los campos incluyendo el RUT
        clienteActualizado.setRut(cliente.getRut());
        clienteActualizado.setNombre(cliente.getNombre());
        clienteActualizado.setApellido(cliente.getApellido());
        clienteActualizado.setCorreo(cliente.getCorreo());
        clienteActualizado.setTelefono(cliente.getTelefono());
        clienteActualizado.setDireccion(cliente.getDireccion());

        return clienteRepository.save(clienteActualizado);
    }

    public Cliente guardarCliente(Cliente cliente) {
        // 1. Validación básica: Que no sea nulo
        if (cliente.getCorreo() == null || cliente.getCorreo().trim().isEmpty()) {
            throw new ClienteCorreoInvalidoException("El correo electrónico es obligatorio para el registro del cliente.");
        }

        // 2. Validación de negocio: Debe contener una estructura de correo básica con '@' y '.'
        if (!cliente.getCorreo().contains("@") || !cliente.getCorreo().contains(".")) {
            throw new ClienteCorreoInvalidoException("Formato de correo electrónico inválido. Debe contener un '@' y un dominio válido (Ej: cliente@correo.com).");
        }

        return clienteRepository.save(cliente);
    }
}
