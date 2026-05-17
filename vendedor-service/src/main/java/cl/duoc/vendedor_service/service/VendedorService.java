package cl.duoc.vendedor_service.service;

import cl.duoc.vendedor_service.dto.VendedorDTO;
import cl.duoc.vendedor_service.mapper.VendedorMapper;
import cl.duoc.vendedor_service.model.Vendedor;
import cl.duoc.vendedor_service.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendedorMapper mapper;

    // Trae todos los vendedores de la BD
    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    // Busca un vendedor por ID y lo transforma a DTO
    public VendedorDTO findById(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        return mapper.toDTO(vendedor);
    }

    // Guarda un nuevo vendedor
    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Borra un vendedor por su ID
    public void delete(Long id) {
        vendedorRepository.deleteById(id);
    }

    // Actualiza los datos de un vendedor existente
    public Vendedor update(Long id, Vendedor vendedor) {
        Vendedor vendedorActualizado = vendedorRepository.findById(id).orElse(null);

        if (vendedorActualizado == null) return null;

        // Actualizamos los campos según el modelo de la imagen
        vendedorActualizado.setRut(vendedor.getRut());
        vendedorActualizado.setNombre(vendedor.getNombre());
        vendedorActualizado.setApellido(vendedor.getApellido());
        vendedorActualizado.setEmail(vendedor.getEmail());
        vendedorActualizado.setPassword(vendedor.getPassword());
        vendedorActualizado.setSueldo(vendedor.getSueldo());
        vendedorActualizado.setFecha_contrato(vendedor.getFecha_contrato());

        return vendedorRepository.save(vendedorActualizado);
    }

}
