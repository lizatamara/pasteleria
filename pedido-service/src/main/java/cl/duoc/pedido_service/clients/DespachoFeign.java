package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.DespachoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "despacho-service")
public interface DespachoFeign {

    @GetMapping("/api/v1/despachos/{id}")
    DespachoDTO obtenerDespacho(@PathVariable Long id);

}