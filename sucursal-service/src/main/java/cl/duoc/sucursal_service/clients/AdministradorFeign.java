package cl.duoc.sucursal_service.clients;


import cl.duoc.sucursal_service.dto.AdministradorDTO;
import cl.duoc.sucursal_service.dto.VendedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "administrador-service", url = "localhost:8085/api/v1")
public interface AdministradorFeign {

    @GetMapping("/administradores/{id}")
    AdministradorDTO obtenerAdministrador(@PathVariable Long id);

}