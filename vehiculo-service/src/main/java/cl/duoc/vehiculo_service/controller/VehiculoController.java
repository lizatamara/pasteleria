package cl.duoc.vehiculo_service.controller;

import cl.duoc.vehiculo_service.dto.VehiculoDTO;
import cl.duoc.vehiculo_service.model.Vehiculo;
import cl.duoc.vehiculo_service.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(vehiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (vehiculoService.findById(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vehiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo vehiculo) {
        // Al llamar al servicio, si los kilos están mal, saltará directo al GlobalExceptionHandler
        Vehiculo nuevoVehiculo = vehiculoService.guardarVehiculo(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo){
        return ResponseEntity.ok(vehiculoService.update(id,vehiculo));
    }


    // Endpoint Reporte 1: GET -> /api/v1/vehiculos/reporte/patente?patente=RD-FJ-12
    @GetMapping("/reporte/patente")
    public ResponseEntity<?> obtenerPorPatente(@RequestParam String patente) {
        Vehiculo vehiculo = vehiculoService.buscarPorPatente(patente);
        if (vehiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado con esa patente");
        }
        return ResponseEntity.ok(vehiculo);
    }

    // Endpoint Reporte 2: GET -> /api/v1/vehiculos/reporte/marca?marca=Toyota
    @GetMapping("/reporte/marca")
    public ResponseEntity<?> listarPorMarca(@RequestParam String marca) {
        return ResponseEntity.ok(vehiculoService.buscarPorMarca(marca));
    }
    // Endpoint Reporte 5: GET -> /api/v1/vehiculos/reporte/modelo?modelo=Hilux
    @GetMapping("/reporte/modelo")
    public ResponseEntity<?> listarPorModelo(@RequestParam String modelo) {
        return ResponseEntity.ok(vehiculoService.buscarPorModelo(modelo));
    }

}
