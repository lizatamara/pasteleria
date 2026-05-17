package cl.duoc.chofer_service.clients;

import cl.duoc.chofer_service.dto.VehiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehiculo-service", url = "localhost:8081/api/v1/vehiculos")
public interface VehiculoFeign {

    @GetMapping("/{id}")
    VehiculoDTO obtenerVehiculo(@PathVariable Long id);


}
