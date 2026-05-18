package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.EventoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "evento-service")
public interface EventoFeign {

    @GetMapping("/api/v1/eventos/{id}")
    EventoDTO obtenerEvento(@PathVariable Long id);
}