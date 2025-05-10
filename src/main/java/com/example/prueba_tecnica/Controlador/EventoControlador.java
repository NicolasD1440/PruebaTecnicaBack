package com.example.prueba_tecnica.Controlador;

import com.example.prueba_tecnica.Servicio.EventoServicio;
import com.example.prueba_tecnica.modelo.Evento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/eventos")
//@CrossOrigin(origins = "*")
public class EventoControlador {
    private final EventoServicio eventoService;

    public EventoControlador(EventoServicio eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> listarEventos() {
        return eventoService.listarEventos();
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevo = eventoService.crearEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
   }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{id}/vender")
    public ResponseEntity<?> venderEntradas(@PathVariable Long id, @RequestParam int cantidad)  {
        Evento nuevo = eventoService.venderBoletos(id, cantidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento actualizado = eventoService.actualizarEvento(id, evento);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        boolean eliminado = eventoService.eliminarEvento(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}

