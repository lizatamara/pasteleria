package cl.duoc.evento_service.controller;

import cl.duoc.evento_service.model.Evento;
import cl.duoc.evento_service.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> registrar(@RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.save(evento), HttpStatus.CREATED);
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

}
