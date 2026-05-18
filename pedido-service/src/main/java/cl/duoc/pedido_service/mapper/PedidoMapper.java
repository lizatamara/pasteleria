package cl.duoc.pedido_service.mapper;

import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {


    // Transforma un solo Pedido (Model) a PedidoDTO
    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setMonto_total(pedido.getMonto_total());
        dto.setFecha_creacion(pedido.getFecha_creacion());
        dto.setHora_creacion(pedido.getHora_creacion());
        dto.setEstado_pedido(pedido.getEstado_pedido());
        dto.setMetodo_pago(pedido.getMetodo_pago());

        return dto;
    }


    public List<PedidoDTO> toListDTO(List<Pedido> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }



}

