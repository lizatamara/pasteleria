package cl.duoc.cliente_service.service;

import cl.duoc.cliente_service.dto.ClienteDTO;
import cl.duoc.cliente_service.mapper.ClienteMapper;
import cl.duoc.cliente_service.model.Cliente;
import cl.duoc.cliente_service.repository.ClienteRepository;
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
}
