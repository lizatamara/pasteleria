package cl.duoc.evento_service.controller;

import cl.duoc.evento_service.model.Evento;
import cl.duoc.evento_service.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(eventoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Object evento = eventoService.findById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardarEvento(evento);
        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Evento evento) {
        Evento actualizado = eventoService.update(id, evento);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/reporte/tipo")
    public ResponseEntity<?> listarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(eventoService.buscarPorTipo(tipo));
    }

    // Endpoint Reporte 2: GET -> /api/v1/eventos/reporte/masivos?cantidad=50
    @GetMapping("/reporte/masivos")
    public ResponseEntity<?> listarMasivos(@RequestParam Integer cantidad) {
        return ResponseEntity.ok(eventoService.buscarMasivos(cantidad));
    }



    // Endpoint Reporte 3: GET -> http://localhost:8083/api/v1/eventos/reporte/fecha?fecha=2026-05-15T14:30:00
    @GetMapping("/reporte/fecha")
    public ResponseEntity<?> listarPorFecha(@RequestParam String fecha) {
        // Al recibir la fecha por texto (ej: 2026-05-15T14:30:00), la parseamos a LocalDateTime
        LocalDateTime fechaParseada = LocalDateTime.parse(fecha);
        return ResponseEntity.ok(eventoService.buscarPorFecha(fechaParseada));
    }

}
