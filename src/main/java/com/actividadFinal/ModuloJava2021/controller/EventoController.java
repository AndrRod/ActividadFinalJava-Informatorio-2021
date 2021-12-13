package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.models.Evento;
import com.actividadFinal.ModuloJava2021.server.EventoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoServer eventoServer;

    @GetMapping(value = "/")
    public ResponseEntity<?> todosLosEventos(){
        ArrayList<Evento> eventos = (ArrayList<Evento>) StreamSupport
                .stream(eventoServer.allEventos().spliterator(), false)
                .collect(Collectors.toList());
        if(!eventos.isEmpty()){
            return ResponseEntity.ok(eventos);
        }
        else return new ResponseEntity<>("no se encuentra agregado ningún evento", HttpStatus.NOT_FOUND);

    }
    @PostMapping(value = "/")
    public ResponseEntity<?> crearEvento (@RequestBody @Valid Evento evento){

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoServer.crearEvento(evento));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> borrarEvento (@PathVariable(value = "id") int idEvento){
        if(!eventoServer.buscarEventoId((long) idEvento).isEmpty()){
            eventoServer.eliminarEvento((long)idEvento);
            return ResponseEntity.ok("El evento fue eliminado exitosamente");
        }
        return new ResponseEntity<>("No hay eventos identificados con esa id", HttpStatus.NOT_FOUND);
    }


}
