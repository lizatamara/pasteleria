package cl.duoc.sucursal_service.service;

import cl.duoc.sucursal_service.clients.AdministradorFeign;
import cl.duoc.sucursal_service.clients.VendedorFeign;
import cl.duoc.sucursal_service.dto.AdministradorDTO;
import cl.duoc.sucursal_service.dto.SucursalDTO;
import cl.duoc.sucursal_service.dto.VendedorDTO;
import cl.duoc.sucursal_service.mapper.SucursalMapper;
import cl.duoc.sucursal_service.model.Sucursal;
import cl.duoc.sucursal_service.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalMapper mapper;

    @Autowired
    private AdministradorFeign personalFeign;

    @Autowired
    private VendedorFeign vendedorFeign; // Va al puerto 8086 (Vendedores)

    public List<SucursalDTO> findAll() {
        List<Sucursal> sucursales = sucursalRepository.findAll();

        return sucursales.stream().map(sucursal -> {
            SucursalDTO sucursalDTO = mapper.toDTO(sucursal);

            // 1. Obtener el Administrador (Uno a Uno)
            if (sucursal.getAdministrador() != null) {
                try {
                    AdministradorDTO adminDTO = personalFeign.obtenerAdministrador(sucursal.getAdministrador());
                    sucursalDTO.setAdministrador(adminDTO);
                } catch (Exception e) {
                    sucursalDTO.setAdministrador(null);
                }
            }

            // 2. CORREGIDO: Obtener la LISTA de vendedores de esta sucursal
            try {
                // Le pasamos el id de la sucursal actual para que nos traiga a sus trabajadores
                List<VendedorDTO> listaVendedores = vendedorFeign.obtenerVendedoresPorSucursal(sucursal.getId());
                sucursalDTO.setVendedores(listaVendedores);
            } catch (Exception e) {
                sucursalDTO.setVendedores(new ArrayList<>()); // Si falla, devolvemos lista vacía para que no explote
            }

            return sucursalDTO;
        }).collect(Collectors.toList());
    }

    public SucursalDTO findById(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal == null) return null;

        SucursalDTO sucursalDTO = mapper.toDTO(sucursal);

        // 1. Obtener el Administrador
        if (sucursal.getAdministrador() != null) {
            try {
                AdministradorDTO adminDTO = personalFeign.obtenerAdministrador(sucursal.getAdministrador());
                sucursalDTO.setAdministrador(adminDTO);
            } catch (Exception e) {
                sucursalDTO.setAdministrador(null);
            }
        }

        // 2. CORREGIDO: Obtener la LISTA de vendedores por ID de sucursal
        try {
            List<VendedorDTO> listaVendedores = vendedorFeign.obtenerVendedoresPorSucursal(sucursal.getId());
            sucursalDTO.setVendedores(listaVendedores);
        } catch (Exception e) {
            sucursalDTO.setVendedores(new ArrayList<>());
        }

        return sucursalDTO;
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void delete(Long id) {
        sucursalRepository.deleteById(id);
    }

    public Sucursal update(Long id, Sucursal sucursal) {
        Sucursal sucursalActualizada = sucursalRepository.findById(id).orElse(null);
        if (sucursalActualizada == null) return null;

        sucursalActualizada.setCodigo(sucursal.getCodigo());
        sucursalActualizada.setNombre(sucursal.getNombre());
        sucursalActualizada.setDireccion(sucursal.getDireccion());
        sucursalActualizada.setTelefono(sucursal.getTelefono());
        sucursalActualizada.setAdministrador(sucursal.getAdministrador()); // Aseguramos el admin

        return sucursalRepository.save(sucursalActualizada);
    }
}