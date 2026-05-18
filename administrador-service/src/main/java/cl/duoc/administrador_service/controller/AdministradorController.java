package cl.duoc.administrador_service.controller;

import cl.duoc.administrador_service.dto.AdministradorDTO;
import cl.duoc.administrador_service.model.Administrador;
import cl.duoc.administrador_service.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(administradorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        // Obtenemos el DTO desde el service
        AdministradorDTO admin = administradorService.findById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Administrador administrador) {
        return new ResponseEntity<>(administradorService.save(administrador), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        administradorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Administrador administrador) {
        Administrador actualizado = administradorService.update(id, administrador);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Endpoint Reporte 1: GET -> /api/v1/administradores/reporte/rut?rut=10.222.333-4
    @GetMapping("/reporte/rut")
    public ResponseEntity<?> obtenerPorRut(@RequestParam String rut) {
        Administrador admin = administradorService.buscarPorRut(rut);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }
        return ResponseEntity.ok(admin);
    }

    // Endpoint Reporte 2: GET -> /api/v1/administradores/reporte/email?email=claudia.r@email.com
    @GetMapping("/reporte/email")
    public ResponseEntity<?> obtenerPorEmail(@RequestParam String email) {
        Administrador admin = administradorService.buscarPorEmail(email);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email no registrado");
        }
        return ResponseEntity.ok(admin);
    }

    // Endpoint Reporte 3: GET -> /api/v1/administradores/reporte/apellido?apellido=Rojas

    @GetMapping("/reporte/apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam String apellido) {
        return ResponseEntity.ok(administradorService.buscarPorApellido(apellido));
    }

}
