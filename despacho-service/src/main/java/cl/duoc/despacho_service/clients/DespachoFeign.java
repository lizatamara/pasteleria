package cl.duoc.despacho_service.clients;

import cl.duoc.despacho_service.dto.ChoferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "chofer-service", url = "localhost:8088/api/v1/choferes")
public interface DespachoFeign {

    @GetMapping("/{id}")
    ChoferDTO obtenerChoferes(@PathVariable Long id);
}