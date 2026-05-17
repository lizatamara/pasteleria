package cl.duoc.chofer_service.controller;

import cl.duoc.chofer_service.dto.ChoferDTO;
import cl.duoc.chofer_service.mapper.ChoferMapper;
import cl.duoc.chofer_service.model.Chofer;
import cl.duoc.chofer_service.service.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("api/v1/choferes")
public class ChoferController {
    @Autowired
    private ChoferService choferService;

    @Autowired
    private  ChoferMapper choferMapper;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(choferService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ChoferDTO choferDTO = choferService.findById(id);
        if (choferDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(choferDTO);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Chofer chofer) {
        return new ResponseEntity<>(choferService.save(chofer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        choferService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Chofer chofer) {
        Chofer choferModificado = choferService.update(id, chofer);
        if (choferModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(choferModificado);
    }


}
