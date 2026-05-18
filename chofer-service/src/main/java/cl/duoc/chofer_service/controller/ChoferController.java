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
    public ResponseEntity<Chofer> crear(@RequestBody Chofer chofer) {
        // REVISA ESTA LÍNEA: Debe llamar a guardarChofer, NO a choferRepository.save o un .save genérico
        Chofer nuevoChofer = choferService.guardarChofer(chofer);
        return new ResponseEntity<>(nuevoChofer, HttpStatus.CREATED);
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




    // Endpoint Reporte 1: GET -> /api/v1/choferes/reporte/rut?rut=11111111-1
    @GetMapping("/reporte/rut")
    public ResponseEntity<?> obtenerPorRut(@RequestParam String rut) {
        Chofer chofer = choferService.buscarPorRut(rut);
        if (chofer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chofer no encontrado con ese RUT");
        }
        return ResponseEntity.ok(chofer);
    }

    // Endpoint Reporte 2: GET -> /api/v1/choferes/reporte/licencia?licencia=Clase A2
    @GetMapping("/reporte/licencia")
    public ResponseEntity<?> listarPorLicencia(@RequestParam String licencia) {
        return ResponseEntity.ok(choferService.buscarPorLicencia(licencia));
    }

    // Endpoint Reporte 3: GET -> /api/v1/choferes/reporte/apellido?apellido=Perez
    @GetMapping("/reporte/apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam String apellido) {
        return ResponseEntity.ok(choferService.buscarPorApellido(apellido));
    }

    // Endpoint Reporte 4: GET -> /api/v1/choferes/reporte/telefono?telefono=%2B56933333333
    @GetMapping("/reporte/telefono")
    public ResponseEntity<?> obtenerPorTelefono(@RequestParam String telefono) {
        Chofer chofer = choferService.buscarPorTelefono(telefono);
        if (chofer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay chofer registrado con ese telefono");
        }
        return ResponseEntity.ok(chofer);
    }

    // Endpoint Reporte 5: GET -> /api/v1/choferes/reporte/vehiculo?vehiculoId=3
    @GetMapping("/reporte/vehiculo")
    public ResponseEntity<?> obtenerPorVehiculo(@RequestParam Long vehiculoId) {
        Chofer chofer = choferService.buscarPorVehiculo(vehiculoId);
        if (chofer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningun chofer asignado a ese ID de vehiculo");
        }
        return ResponseEntity.ok(chofer);
    }

}
