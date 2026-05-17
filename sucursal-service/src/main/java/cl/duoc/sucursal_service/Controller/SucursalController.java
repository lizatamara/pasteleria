package cl.duoc.sucursal_service.Controller;

import cl.duoc.sucursal_service.dto.SucursalDTO;
import cl.duoc.sucursal_service.model.Sucursal;
import cl.duoc.sucursal_service.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        SucursalDTO sucursalDTO = sucursalService.findById(id);
        if (sucursalDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalDTO);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Sucursal sucursal) {
        return new ResponseEntity<>(sucursalService.save(sucursal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal sucursalModificada = sucursalService.update(id, sucursal);
        if (sucursalModificada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalModificada);
    }
}