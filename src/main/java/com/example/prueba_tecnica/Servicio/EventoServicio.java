package com.example.prueba_tecnica.Servicio;

import com.example.prueba_tecnica.Repositorio.EventoRepositorio;
import com.example.prueba_tecnica.modelo.Evento;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoServicio {
    private final EventoRepositorio eventoRepository;

    public EventoServicio(EventoRepositorio eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento actualizarEvento(Long id, Evento eventoActualizado) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        // Actualizar campos (excepto ID)
        evento.setNombre(eventoActualizado.getNombre());
        evento.setFechaHora(eventoActualizado.getFechaHora());
        evento.setUbicacion(eventoActualizado.getUbicacion());
        evento.setBoletosDisponibles(eventoActualizado.getBoletosDisponibles());
        evento.setPrecio(eventoActualizado.getPrecio());

        return eventoRepository.save(evento);
    }
    public boolean eliminarEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Evento venderBoletos(Long id, int cantidad) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        if (cantidad > evento.getBoletosDisponibles()) {
            throw new IllegalArgumentException("No hay suficientes boletos disponibles");
        }
        evento.setBoletosDisponibles(evento.getBoletosDisponibles() - cantidad);
        return eventoRepository.save(evento);
    }

}
