package com.actividadFinal.ModuloJava2021.server;

import com.actividadFinal.ModuloJava2021.models.Evento;
import com.actividadFinal.ModuloJava2021.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventoServer {

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public Evento crearEvento(Evento evento){return eventoRepository.save(evento);}

    @Transactional
    public void eliminarEvento(Long id){eventoRepository.deleteById(id);}

    @Transactional
    public Optional<Evento> buscarEventoId(Long id){return eventoRepository.findById(id);}

    @Transactional
    public Iterable<Evento> allEventos(){return eventoRepository.findAll();}



}
