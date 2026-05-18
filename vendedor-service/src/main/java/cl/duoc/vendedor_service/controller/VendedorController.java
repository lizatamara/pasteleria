package cl.duoc.vendedor_service.controller;

import cl.duoc.vendedor_service.dto.VendedorDTO;
import cl.duoc.vendedor_service.model.Vendedor;
import cl.duoc.vendedor_service.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        // Obtenemos el DTO desde el service
        VendedorDTO vendedor = vendedorService.findById(id);
        if (vendedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Vendedor vendedor) {
        // Llama al servicio para que ejecute los filtros correctamente
        Vendedor nuevoVendedor = vendedorService.guardarVendedor(vendedor);
        return new ResponseEntity<>(nuevoVendedor, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        Vendedor actualizado = vendedorService.update(id, vendedor);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Endpoint Reporte 1: GET -> /api/v1/vendedores/reporte/rut?rut=17.444.555-6
    @GetMapping("/reporte/rut")
    public ResponseEntity<?> obtenerPorRut(@RequestParam String rut) {
        Vendedor vendedor = vendedorService.buscarPorRut(rut);
        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado");
        }
        return ResponseEntity.ok(vendedor);
    }

    // Endpoint Reporte 2: GET -> /api/v1/vendedores/reporte/sueldo?sueldo=600000
    @GetMapping("/reporte/sueldo")
    public ResponseEntity<?> listarPorSueldoAlto(@RequestParam Integer sueldo) {
        return ResponseEntity.ok(vendedorService.buscarPorSueldoAlto(sueldo));
    }

    // Endpoint Reporte 3: GET -> /api/v1/vendedores/reporte/apellido?apellido=Munoz
    @GetMapping("/reporte/apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam String apellido) {
        return ResponseEntity.ok(vendedorService.buscarPorApellido(apellido));
    }



}
