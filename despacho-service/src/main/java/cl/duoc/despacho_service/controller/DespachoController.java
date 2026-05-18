package cl.duoc.despacho_service.controller;

import cl.duoc.despacho_service.dto.DespachoDTO;
import cl.duoc.despacho_service.model.Despacho;
import cl.duoc.despacho_service.service.DespachoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    // Endpoint Reporte 1: GET -> /api/v1/despachos/reporte/estado?estado=En camino
    @GetMapping("/reporte/estado")
    public ResponseEntity<List<Despacho>> listarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(despachoService.buscarPorEstado(estado));
    }

    // Endpoint Reporte 2: GET -> /api/v1/despachos/reporte/comuna?comuna=La Florida
    @GetMapping("/reporte/comuna")
    public ResponseEntity<List<Despacho>> listarPorComuna(@RequestParam String comuna) {
        return ResponseEntity.ok(despachoService.buscarPorComuna(comuna));
    }

    // Endpoint Reporte 3: GET -> /api/v1/despachos/reporte/fecha?fecha=2026-05-18
    @GetMapping("/reporte/fecha")
    public ResponseEntity<List<Despacho>> listarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(despachoService.buscarPorFecha(fecha));
    }
    //buscar y listar todos los despachos programados que se deben entregar desde una fecha específica hacia el futuro
    // Endpoint Reporte 4: GET -> /api/v1/despachos/reporte/futuros?fecha=2026-05-17
    @GetMapping("/reporte/futuros")
    public ResponseEntity<List<Despacho>> listarFuturos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(despachoService.buscarPorFechasFuturas(fecha));
    }



    // Endpoint Reporte 5: GET -> /api/v1/despachos/reporte/chofer?choferId=1
    @GetMapping("/reporte/chofer")
    public ResponseEntity<List<Despacho>> listarPorChofer(@RequestParam Long choferId) {
        return ResponseEntity.ok(despachoService.buscarPorChofer(choferId));
    }

}