package cl.duoc.evento_service.service;

import cl.duoc.evento_service.dto.EventoDTO;
import cl.duoc.evento_service.exception.EventoCapacidadInvalidaException;
import cl.duoc.evento_service.mapper.EventoMapper;
import cl.duoc.evento_service.model.Evento;
import cl.duoc.evento_service.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoMapper mapper;

    // Trae todos los eventos del historial
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscarPorTipo(String tipo) {
        return eventoRepository.findByTipoEventoContainingIgnoreCase(tipo);
    }

    public List<Evento> buscarMasivos(Integer cantidad) {
        // Llama al método manual que creamos recién
        return eventoRepository.buscarPorCantidadPersonas(cantidad);
    }

    public List<Evento> buscarPorFecha(LocalDateTime fecha) {
        return eventoRepository.findByFecha(fecha);
    }



    // Busca un evento por ID y lo transforma a DTO
    public EventoDTO findById(Long id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        return mapper.toDTO(evento);
    }

    // Guarda un nuevo evento
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Borra un registro de evento por su ID
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    // Actualiza un evento existente
    public Evento update(Long id, Evento evento) {
        Evento eventoActualizado = eventoRepository.findById(id).orElse(null);

        if (eventoActualizado == null) return null;

        eventoActualizado.setTipoEvento(evento.getTipoEvento());
        eventoActualizado.setFecha(evento.getFecha());
        eventoActualizado.setCantidad_personas(evento.getCantidad_personas());

        return eventoRepository.save(eventoActualizado);
    }
    public Evento guardarEvento(Evento evento) {
        // 1. Validación: Aforo mínimo para que sea rentable el evento
        if (evento.getCantidad_personas() < 20) {
            throw new EventoCapacidadInvalidaException("Capacidad insuficiente. El servicio de banquetería de eventos requiere un mínimo de 20 personas.");
        }

        // 2. Validación: Límite máximo operativo de la cocina
        if (evento.getCantidad_personas() > 350) {
            throw new EventoCapacidadInvalidaException("Capacidad excedida. El límite de producción actual por evento es de un máximo de 350 personas.");
        }

        return eventoRepository.save(evento);
    }

}
