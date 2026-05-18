package cl.duoc.sucursal_service.service;

import cl.duoc.sucursal_service.clients.AdministradorFeign;
import cl.duoc.sucursal_service.clients.VendedorFeign;
import cl.duoc.sucursal_service.dto.AdministradorDTO;
import cl.duoc.sucursal_service.dto.SucursalDTO;
import cl.duoc.sucursal_service.mapper.SucursalMapper;
import cl.duoc.sucursal_service.model.Sucursal;
import cl.duoc.sucursal_service.repository.SucursalRepository;
import cl.duoc.sucursal_service.exception.SucursalCodigoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalMapper mapper;

    @Autowired
    private AdministradorFeign administradorFeign;

    @Autowired
    private VendedorFeign vendedorFeign;

    // =========================
    // ENRICH CENTRALIZADO
    // =========================
    private SucursalDTO enrich(Sucursal s) {
        SucursalDTO dto = mapper.toDTO(s);

        // ADMINISTRADOR (UNO A UNO)
        try {
            if (s.getAdministrador() != null) {
                AdministradorDTO admin =
                        administradorFeign.obtenerAdministrador(s.getAdministrador());
                dto.setAdministrador(admin);
            }
        } catch (Exception e) {
            dto.setAdministrador(null);
        }

        // VENDEDORES (UNO A MUCHOS)
        try {
            dto.setVendedores(
                    vendedorFeign.obtenerVendedoresPorSucursal(s.getId())
            );
        } catch (Exception e) {
            dto.setVendedores(List.of());
        }

        return dto;
    }

    // =========================
    // CRUD
    // =========================

    public List<SucursalDTO> findAll() {
        return sucursalRepository.findAll()
                .stream()
                .map(this::enrich)
                .toList();
    }

    public SucursalDTO findById(Long id) {
        return sucursalRepository.findById(id)
                .map(this::enrich)
                .orElse(null);
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void delete(Long id) {
        sucursalRepository.deleteById(id);
    }

    public Sucursal update(Long id, Sucursal sucursal) {
        Sucursal s = sucursalRepository.findById(id).orElse(null);
        if (s == null) return null;

        s.setCodigo(sucursal.getCodigo());
        s.setNombre(sucursal.getNombre());
        s.setDireccion(sucursal.getDireccion());
        s.setTelefono(sucursal.getTelefono());
        s.setAdministrador(sucursal.getAdministrador());

        return sucursalRepository.save(s);
    }

    // =========================
    // VALIDACIÓN NEGOCIO
    // =========================
    public Sucursal guardarSucursal(Sucursal sucursal) {

        if (sucursal.getCodigo() == null || sucursal.getCodigo().isBlank()) {
            throw new SucursalCodigoInvalidoException("El código es obligatorio");
        }

        if (!sucursal.getCodigo().startsWith("SUC-")) {
            throw new SucursalCodigoInvalidoException(
                    "El código debe iniciar con SUC-"
            );
        }

        return sucursalRepository.save(sucursal);
    }

    // =========================
    // REPORTES
    // =========================

    public SucursalDTO buscarPorCodigo(String codigo) {
        return sucursalRepository.findAll().stream()
                .filter(s -> s.getCodigo().equalsIgnoreCase(codigo))
                .map(this::enrich)
                .findFirst()
                .orElse(null);
    }

    public List<SucursalDTO> buscarPorNombre(String nombre) {
        return sucursalRepository.findAll().stream()
                .filter(s -> s.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .map(this::enrich)
                .toList();
    }

    public List<SucursalDTO> buscarPorComuna(String comuna) {
        return sucursalRepository.findAll().stream()
                .filter(s -> s.getDireccion().toLowerCase().contains(comuna.toLowerCase()))
                .map(this::enrich)
                .toList();
    }

    public SucursalDTO buscarPorTelefono(String telefono) {
        return sucursalRepository.findAll().stream()
                .filter(s -> s.getTelefono().equals(telefono))
                .map(this::enrich)
                .findFirst()
                .orElse(null);
    }

    public List<SucursalDTO> buscarPorFiltroDoble(String nombre, String comuna) {
        return sucursalRepository.findAll().stream()
                .filter(s ->
                        s.getNombre().toLowerCase().contains(nombre.toLowerCase()) &&
                                s.getDireccion().toLowerCase().contains(comuna.toLowerCase())
                )
                .map(this::enrich)
                .toList();
    }
}