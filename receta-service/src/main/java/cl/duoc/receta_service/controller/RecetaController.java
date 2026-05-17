package cl.duoc.receta_service.controller;

import cl.duoc.receta_service.model.Receta;
import cl.duoc.receta_service.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recetas")
public class RecetaController {
    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(recetaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        // Usamos el Service que ya devuelve el DTO
        Object receta = recetaService.findById(id);
        if (receta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(receta);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Receta receta) {
        return new ResponseEntity<>(recetaService.save(receta), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        Receta actualizada = recetaService.update(id, receta);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }



}
