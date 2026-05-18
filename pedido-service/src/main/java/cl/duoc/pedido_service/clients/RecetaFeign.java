package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.RecetaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "receta-service")
public interface RecetaFeign {

    @GetMapping("/api/v1/recetas/{id}")
    RecetaDTO obtenerReceta(@PathVariable Long id);
}