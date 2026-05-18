package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteFeign {

    @GetMapping("/api/v1/clientes/{id}")
    ClienteDTO obtenerCliente(@PathVariable Long id);
}