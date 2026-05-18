package cl.duoc.pedido_service.service;

import cl.duoc.pedido_service.clients.*;
import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.dto.EventoDTO; // Aseguramos la importación del DTO
import cl.duoc.pedido_service.dto.RecetaDTO;
import cl.duoc.pedido_service.exception.PedidoMontoInvalidoException;
import cl.duoc.pedido_service.mapper.PedidoMapper;
import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper mapper;

    // Inyección de todos los clientes OpenFeign independientes
    @Autowired
    private SucursalFeign sucursalFeign;

    @Autowired
    private ClienteFeign clienteFeign;



    @Autowired
    private DespachoFeign despachoFeign;

    @Autowired
    private RecetaFeign recetaFeign;

    @Autowired
    private EventoFeign eventoFeign;





    // Retorna la lista de todos los pedidos mapeados a DTO y con datos remotos cargados
    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream().map(pedido -> {
            PedidoDTO pedidoDTO = mapper.toDTO(pedido);

            // 1. Cargar datos de la Sucursal
            if (pedido.getSucursal() != null) {
                try {
                    pedidoDTO.setSucursal(sucursalFeign.obtenerSucursal(pedido.getSucursal()));
                } catch (Exception e) {
                    pedidoDTO.setSucursal(null);
                }
            }

            // 2. Cargar datos del Cliente
            if (pedido.getCliente() != null) {
                try {
                    pedidoDTO.setCliente(clienteFeign.obtenerCliente(pedido.getCliente()));
                } catch (Exception e) {
                    pedidoDTO.setCliente(null);
                }
            }

            // 3. Cargar datos del Despacho
            if (pedido.getDespacho() != null) {
                try {
                    pedidoDTO.setDespacho(despachoFeign.obtenerDespacho(pedido.getDespacho()));
                } catch (Exception e) {
                    System.err.println("--- ERROR EN DESPACHO FEIGN (findAll) ---");
                    e.printStackTrace();
                    pedidoDTO.setDespacho(null);
                }
            }

            // 4. Cargar datos de la Receta
            if (pedido.getReceta() != null) {
                try {
                    pedidoDTO.setReceta(recetaFeign.obtenerReceta(pedido.getReceta()));
                } catch (Exception e) {
                    pedidoDTO.setReceta(null);
                }
            }

            // 5. Cargar datos del Evento (MODIFICADO AQUÍ)
            if (pedido.getEvento() != null) {
                try {
                    EventoDTO eventoDTO = eventoFeign.obtenerEvento(pedido.getEvento());

                    // Si el objeto se creó pero el id llegó null, lo parchamos a mano
                    if (eventoDTO != null && eventoDTO.getId() == null) {
                        eventoDTO.setId(pedido.getEvento());
                    }

                    pedidoDTO.setEvento(eventoDTO);
                } catch (Exception e) {
                    pedidoDTO.setEvento(null);
                }
            }

            return pedidoDTO;
        }).collect(Collectors.toList());
    }

    // Busca un pedido específico por ID con toda su información remota
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) return null;

        PedidoDTO pedidoDTO = mapper.toDTO(pedido);

        if (pedido.getSucursal() != null) {
            try {
                pedidoDTO.setSucursal(sucursalFeign.obtenerSucursal(pedido.getSucursal()));
            } catch (Exception e) {
                pedidoDTO.setSucursal(null);
            }
        }

        if (pedido.getCliente() != null) {
            try {
                pedidoDTO.setCliente(clienteFeign.obtenerCliente(pedido.getCliente()));
            } catch (Exception e) {
                pedidoDTO.setCliente(null);
            }
        }

        if (pedido.getDespacho() != null) {
            try {
                pedidoDTO.setDespacho(despachoFeign.obtenerDespacho(pedido.getDespacho()));
            } catch (Exception e) {
                pedidoDTO.setDespacho(null);
            }
        }

        if (pedido.getReceta() != null) {
            try {
                pedidoDTO.setReceta(recetaFeign.obtenerReceta(pedido.getReceta()));
            } catch (Exception e) {
                pedidoDTO.setReceta(null);
            }
        }

        // 5. Cargar datos del Evento (MODIFICADO AQUÍ TAMBIÉN)
        if (pedido.getEvento() != null) {
            try {
                EventoDTO eventoDTO = eventoFeign.obtenerEvento(pedido.getEvento());

                // Si el objeto se creó pero el id llegó null, lo parchamos a mano
                if (eventoDTO != null && eventoDTO.getId() == null) {
                    eventoDTO.setId(pedido.getEvento());
                }

                pedidoDTO.setEvento(eventoDTO);
            } catch (Exception e) {
                pedidoDTO.setEvento(null);
            }
        }

        return pedidoDTO;
    }


    // Reporte 1: Buscar por Estado
    public List<PedidoDTO> findByEstado(String estado) {
        return mappedRemoteData(pedidoRepository.buscarPorEstado(estado));
    }

    // Reporte 2: Buscar por Monto Mínimo
    public List<PedidoDTO> findByMontoMinimo(Integer monto) {
        return mappedRemoteData(pedidoRepository.buscarPorMontoMinimo(monto));
    }

    // Reporte 3: Buscar por Método de Pago
    public List<PedidoDTO> findByMetodoPago(String metodo) {
        return mappedRemoteData(pedidoRepository.buscarPorMetodoPago(metodo));
    }

    // Reporte 4: Pedidos Críticos
    public List<PedidoDTO> findPedidosCriticos(String estado, Integer monto) {
        return mappedRemoteData(pedidoRepository.buscarPedidosCriticos(estado, monto));
    }

    // Reporte 5: Buscar por Rango de Fechas
    public List<PedidoDTO> findByRangoFechas(String inicio, String fin) {
        java.time.LocalDate fechaInicio = java.time.LocalDate.parse(inicio);
        java.time.LocalDate fechaFin = java.time.LocalDate.parse(fin);
        return mappedRemoteData(pedidoRepository.buscarPorRangoFechas(fechaInicio, fechaFin));
    }






    // EL ÚNICO MOTOR FEIGN CENTRALIZADO (Mapea todo a DTO y procesa la cantidad automáticamente)
    private List<PedidoDTO> mappedRemoteData(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> {
            PedidoDTO pedidoDTO = mapper.toDTO(pedido);

            if (pedido.getSucursal() != null) {
                try { pedidoDTO.setSucursal(sucursalFeign.obtenerSucursal(pedido.getSucursal())); } catch (Exception e) {}
            }
            if (pedido.getCliente() != null) {
                try { pedidoDTO.setCliente(clienteFeign.obtenerCliente(pedido.getCliente())); } catch (Exception e) {}
            }
            if (pedido.getDespacho() != null) {
                try { pedidoDTO.setDespacho(despachoFeign.obtenerDespacho(pedido.getDespacho())); } catch (Exception e) {}
            }
            if (pedido.getReceta() != null) {
                try { pedidoDTO.setReceta(recetaFeign.obtenerReceta(pedido.getReceta())); } catch (Exception e) {}
            }
            if (pedido.getEvento() != null) {
                try {
                    cl.duoc.pedido_service.dto.EventoDTO eventoDTO = eventoFeign.obtenerEvento(pedido.getEvento());
                    if (eventoDTO != null && eventoDTO.getId() == null) {
                        eventoDTO.setId(pedido.getEvento());
                    }
                    pedidoDTO.setEvento(eventoDTO);
                } catch (Exception e) {}
            }
            return pedidoDTO;
        }).collect(Collectors.toList());
    }







    // Elimina un pedido por ID
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    // Actualiza los datos de un pedido existente (para el PUT del controller)
    public Pedido update(Long id, Pedido pedido) {
        Pedido pedidoActualizado = pedidoRepository.findById(id).orElse(null);

        if (pedidoActualizado == null) return null;

        // Actualizamos los campos directos que vienen de tu diagrama de clases de Pedido
        pedidoActualizado.setMonto_total(pedido.getMonto_total());
        pedidoActualizado.setFecha_creacion(pedido.getFecha_creacion());
        pedidoActualizado.setHora_creacion(pedido.getHora_creacion());
        pedidoActualizado.setEstado_pedido(pedido.getEstado_pedido());
        pedidoActualizado.setMetodo_pago(pedido.getMetodo_pago());

        // Mantener al día las llaves foráneas lógicas por si cambian de ID
        pedidoActualizado.setSucursal(pedido.getSucursal());
        pedidoActualizado.setCliente(pedido.getCliente());
        pedidoActualizado.setDespacho(pedido.getDespacho());
        pedidoActualizado.setReceta(pedido.getReceta());
        pedidoActualizado.setEvento(pedido.getEvento());

        return pedidoRepository.save(pedidoActualizado);
    }
    public Pedido save(Pedido pedido) {
        // 1. Validación básica: Que el monto no sea nulo o negativo
        if (pedido.getMonto_total() == null || pedido.getMonto_total() <= 0) {
            throw new PedidoMontoInvalidoException("El monto total del pedido debe ser un valor mayor a $0.");
        }

        // 2. REGLA DE NEGOCIO: Monto mínimo de compra en la pastelería ($5.000)
        if (pedido.getMonto_total() < 5000) {
            throw new PedidoMontoInvalidoException("Monto insuficiente. Para procesar un pedido y agendar producción/despacho, el valor mínimo de compra es de $5.000.");
        }

        return pedidoRepository.save(pedido);
    }


}