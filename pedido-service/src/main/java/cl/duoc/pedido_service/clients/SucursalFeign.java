package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sucursal-service")
public interface SucursalFeign {

    @GetMapping("/api/v1/sucursales/{id}")
    SucursalDTO obtenerSucursal(@PathVariable Long id);

}