package cl.duoc.cliente_service.controller;

import cl.duoc.cliente_service.model.Cliente;
import cl.duoc.cliente_service.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        // Obtenemos el DTO desde el service
        Object cliente = clienteService.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente actualizado = clienteService.update(id, cliente);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Endpoint Reporte 1: GET -> http://localhost:8084/api/v1/clientes/reporte/rut?rut=12.345.678-9
    @GetMapping("/reporte/rut")
    public ResponseEntity<?> obtenerPorRut(@RequestParam String rut) {
        Cliente cliente = clienteService.buscarPorRut(rut);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        return ResponseEntity.ok(cliente);
    }

    // Endpoint Reporte 2: GET -> /api/v1/clientes/reporte/comuna?comuna=La Florida
    @GetMapping("/reporte/comuna")
    public ResponseEntity<?> listarPorComuna(@RequestParam String comuna) {
        return ResponseEntity.ok(clienteService.buscarPorComuna(comuna));
    }

    // Endpoint Reporte 3: GET -> /api/v1/clientes/reporte/apellido?apellido=Perez
    @GetMapping("/reporte/apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam String apellido) {
        return ResponseEntity.ok(clienteService.buscarPorApellido(apellido));
    }

    // Endpoint Reporte 4: GET -> /api/v1/clientes/reporte/email?email=juan.perez@email.com
    @GetMapping("/reporte/email")
    public ResponseEntity<?> obtenerPorEmail(@RequestParam String email) {
        Cliente cliente = clienteService.buscarPorEmail(email); // El servicio que arreglamos arriba
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ese correo");
        }
        return ResponseEntity.ok(cliente);
    }

    // Endpoint Reporte 5: GET -> /api/v1/clientes/reporte/nombre?nombre=Juan
    @GetMapping("/reporte/nombre")
    public ResponseEntity<?> listarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(clienteService.buscarPorNombre(nombre));
    }
}
