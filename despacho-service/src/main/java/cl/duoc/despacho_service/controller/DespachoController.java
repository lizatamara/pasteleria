package cl.duoc.despacho_service.controller;

import cl.duoc.despacho_service.dto.DespachoDTO;
import cl.duoc.despacho_service.model.Despacho;
import cl.duoc.despacho_service.service.DespachoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/despachos")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(despachoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        DespachoDTO despachoDTO = despachoService.findById(id);
        if (despachoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(despachoDTO);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Despacho despacho) {
        return new ResponseEntity<>(despachoService.save(despacho), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        despachoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Despacho despacho) {
        Despacho despachoModificado = despachoService.update(id, despacho);
        if (despachoModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(despachoModificado);
    }
}