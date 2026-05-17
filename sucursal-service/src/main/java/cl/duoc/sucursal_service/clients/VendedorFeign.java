package cl.duoc.sucursal_service.clients;

import cl.duoc.sucursal_service.dto.VendedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "vendedor-service", url = "localhost:8086/api/v1")
public interface VendedorFeign {

    @GetMapping("/vendedores/sucursal/{sucursalId}")
    List<VendedorDTO> obtenerVendedoresPorSucursal(@PathVariable("sucursalId") Long sucursalId);
}