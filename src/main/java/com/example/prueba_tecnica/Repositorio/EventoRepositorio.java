package com.example.prueba_tecnica.Repositorio;

import com.example.prueba_tecnica.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepositorio extends JpaRepository<Evento, Long> {
}
