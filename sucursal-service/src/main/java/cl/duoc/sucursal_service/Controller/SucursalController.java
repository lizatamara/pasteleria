package cl.duoc.sucursal_service.Controller;

import cl.duoc.sucursal_service.dto.SucursalDTO;
import cl.duoc.sucursal_service.model.Sucursal;
import cl.duoc.sucursal_service.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    // =====================
    // CRUD BASE
    // =====================

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> buscarPorId(@PathVariable Long id) {
        SucursalDTO dto = sucursalService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Sucursal> registrar(@RequestBody Sucursal sucursal) {
        return new ResponseEntity<>(
                sucursalService.guardarSucursal(sucursal),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(
            @PathVariable Long id,
            @RequestBody Sucursal sucursal
    ) {
        Sucursal actualizado = sucursalService.update(id, sucursal);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =====================
    // REPORTES
    // =====================

    @GetMapping("/reporte/codigo")
    public ResponseEntity<SucursalDTO> porCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(sucursalService.buscarPorCodigo(codigo));
    }

    @GetMapping("/reporte/nombre")
    public ResponseEntity<List<SucursalDTO>> porNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(sucursalService.buscarPorNombre(nombre));
    }

    @GetMapping("/reporte/comuna")
    public ResponseEntity<List<SucursalDTO>> porComuna(@RequestParam String comuna) {
        return ResponseEntity.ok(sucursalService.buscarPorComuna(comuna));
    }

    @GetMapping("/reporte/telefono")
    public ResponseEntity<SucursalDTO> porTelefono(@RequestParam String telefono) {
        return ResponseEntity.ok(sucursalService.buscarPorTelefono(telefono));
    }

    @GetMapping("/reporte/filtro-doble")
    public ResponseEntity<List<SucursalDTO>> filtroDoble(
            @RequestParam String nombre,
            @RequestParam String comuna
    ) {
        return ResponseEntity.ok(
                sucursalService.buscarPorFiltroDoble(nombre, comuna)
        );
    }
}