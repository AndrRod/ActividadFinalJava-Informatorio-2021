package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.DTOS.EventoRankingDto;
import com.actividadFinal.ModuloJava2021.models.Evento;
import com.actividadFinal.ModuloJava2021.models.Voto;
import com.actividadFinal.ModuloJava2021.repository.VotoRepository;
import com.actividadFinal.ModuloJava2021.server.EventoServer;
import com.actividadFinal.ModuloJava2021.server.VotoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoServer eventoServer;
    private VotoRepository votoRepository;
    @Autowired
    private VotoServer votoServer;

    private VotoController votoController;
    private EventoRankingDto eventoRankingDto;

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
    public ResponseEntity<Object> borrarEvento(@PathVariable(value = "id") int idEvento){
        if(eventoServer.buscarEventoId((long) idEvento).isPresent()){
            eventoServer.eliminarEvento((long)idEvento);
            return ResponseEntity.ok("El evento fue eliminado exitosamente");
        }
        return new ResponseEntity<>("No hay eventos identificados con esa id o ingresó un dato invalido", HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/{id}")
    ResponseEntity<?> modifiarEvento(@RequestBody @Valid Evento eventoModif, @PathVariable(value = "id") @Valid int idEvento){
        Optional<Evento> evento = eventoServer.buscarEventoId((long) idEvento);
        if(!evento.isPresent()){
            return new ResponseEntity<>("No se encuentra ningún evento con esa id", HttpStatus.NOT_FOUND);
        }
        evento.get().setEstadoEvento(eventoModif.getEstadoEvento());
        evento.get().setDetallesEvento(eventoModif.getDetallesEvento());
        evento.get().setFechaCierre(eventoModif.getFechaCierre());
        evento.get().setPremio(eventoModif.getPremio());
        return ResponseEntity.ok(eventoServer.crearEvento(evento.get()));
    }

    @GetMapping(value = "rankingEventoId/{id}")
    ResponseEntity<?> rankingEvento(@PathVariable(value = "id") int idEvento){
//        Optional<Evento> evento = eventoServer.buscarEventoId((long) idEvento);
        List<Voto> voto = votoServer.buscarVotoPorEvento((long) idEvento);

//        voto.stream().filter(x-> voto.contains(x.getVotoAEmprendimiento().getNombre().)).count();
//        Map<Emprendimiento, Integer> map = eventoRankingDto.EventosDto(idEvento);

        return ResponseEntity.ok(voto);

    }

//tengo que poner el id del evneto y tiene que salir cuantos votos tiene cada emprendimiento
}
