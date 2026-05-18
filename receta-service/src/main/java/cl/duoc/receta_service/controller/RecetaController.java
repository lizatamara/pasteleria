package cl.duoc.receta_service.controller;

import cl.duoc.receta_service.model.Receta;
import cl.duoc.receta_service.service.RecetaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> registrar(@Valid @RequestBody Receta receta) {
        // Llama al método validado que creamos en el servicio
        Receta nuevaReceta = recetaService.guardarReceta(receta);
        return new ResponseEntity<>(nuevaReceta, HttpStatus.CREATED);
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

    // filtrar por categoria
    // Endpoint Reporte 1: GET -> /api/v1/recetas/reporte/categoria?categoria=Tortas
    @GetMapping("/reporte/categoria")
    public ResponseEntity<?> listarPorCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(recetaService.buscarPorCategoria(categoria));
    }

    //precio maximo

    // Endpoint Reporte 2: GET -> /api/v1/recetas/reporte/baratos?precio=15000
    @GetMapping("/reporte/baratos")
    public ResponseEntity<?> listarPorPrecioMaximo(@RequestParam Integer precio) {
        return ResponseEntity.ok(recetaService.buscarPorPrecioMaximo(precio));
    }



    // Endpoint Reporte 3: GET -> /api/vi/recetas/reporte/buscar?nombre=Pie de Limón
    @GetMapping("/reporte/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(recetaService.buscarPorNombre(nombre));
    }


}
