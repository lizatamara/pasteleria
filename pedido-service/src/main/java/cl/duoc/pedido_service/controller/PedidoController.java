package cl.duoc.pedido_service.controller;

import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    // Listar todos los pedidos con sus datos remotos (Feign) resueltos
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    // Buscar un pedido específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.findById(id);
        if (pedidoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoDTO);
    }

    // Registrar un nuevo pedido (recibe el JSON plano con los IDs)
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED);
    }

    // Borrar un pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar los datos de un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoModificado = pedidoService.update(id, pedido);
        if (pedidoModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoModificado);
    }


    //http://localhost:8092/api/v1/pedidos/reporte/estado?estado=Pendiente
    // 1. Reporte por Estado (?estado=Pendiente)
    @GetMapping("/reporte/estado")
    public ResponseEntity<List<PedidoDTO>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(pedidoService.findByEstado(estado));
    }

    // 2.http://localhost:8092/api/v1/pedidos/reporte/monto-mayor?monto=25000
    @GetMapping("/reporte/monto-mayor")
    public ResponseEntity<List<PedidoDTO>> buscarPorMontoMayor(@RequestParam Integer monto) {
        return ResponseEntity.ok(pedidoService.findByMontoMinimo(monto));
    }

    // 3.http://localhost:8092/api/v1/pedidos/reporte/metodo-pago?metodo=Tarjeta de Crédito
    @GetMapping("/reporte/metodo-pago")
    public ResponseEntity<List<PedidoDTO>> buscarPorMetodoPago(@RequestParam String metodo) {
        return ResponseEntity.ok(pedidoService.findByMetodoPago(metodo));
    }

    // 4. http://localhost:8092/api/v1/pedidos/reporte/criticos?estado=Pendiente&montoCritico=20000
    @GetMapping("/reporte/criticos")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosCriticos(
            @RequestParam String estado,
            @RequestParam Integer montoCritico) {
        return ResponseEntity.ok(pedidoService.findPedidosCriticos(estado, montoCritico));
    }


    // REPORTE DIARIO: Filtra las ventas de hoy en tiempo real.
// Sirve para que el dueño sepa qué cocinar y qué despachar en el día, sin enredarse con fechas pasadas.
    // 5. http://localhost:8092/api/v1/pedidos/reporte/fechas?inicio=2026-05-16&fin=2026-05-17
    @GetMapping("/reporte/fechas")
    public ResponseEntity<List<PedidoDTO>> buscarPorFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        return ResponseEntity.ok(pedidoService.findByRangoFechas(inicio, fin));
    }

}


