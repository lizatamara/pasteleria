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
    public ResponseEntity<?> registrar(@RequestBody Vehiculo vehiculo){
        return new ResponseEntity<>(vehiculoService.save(vehiculo), HttpStatus.CREATED) ;
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
}
