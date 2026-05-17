package cl.duoc.vendedor_service.controller;

import cl.duoc.vendedor_service.dto.VendedorDTO;
import cl.duoc.vendedor_service.model.Vendedor;
import cl.duoc.vendedor_service.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> registrar(@RequestBody Vendedor vendedor) {
        return new ResponseEntity<>(vendedorService.save(vendedor), HttpStatus.CREATED);
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

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Vendedor>> obtenerVendedoresPorSucursal(@PathVariable Long sucursalId) {
        List<Vendedor> lista = vendedorService.obtenerVendedoresPorSucursal(sucursalId);
        return ResponseEntity.ok(lista);
    }
}
