package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.dtos.VotoDto;
import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.actividadFinal.ModuloJava2021.entity.Evento;
import com.actividadFinal.ModuloJava2021.entity.Usuario;
import com.actividadFinal.ModuloJava2021.entity.Voto;
import com.actividadFinal.ModuloJava2021.repository.EmprendimientoRepository;
import com.actividadFinal.ModuloJava2021.repository.EventoRepository;
import com.actividadFinal.ModuloJava2021.repository.UsuarioRepository;
import com.actividadFinal.ModuloJava2021.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private EventoRepository eventoRepository;

//    @Autowired
//    private UsuarioServer usuarioServer;

    private VotoDto votoDto;
    @PostMapping("/{idUsuario}/{idEvento}/{idEmprendimiento}")
    public ResponseEntity<?> altaVoto(@RequestBody @Valid Voto voto,
                                      @PathVariable("idUsuario") Long idUsuario,
                                      @PathVariable("idEvento") Long idEvento,
                                      @PathVariable("idEmprendimiento") Long idEmprendimiento){
//
        Evento evento = eventoRepository.getById(idEvento);
        Usuario usuario = usuarioRepository.getById(idUsuario);
        Emprendimiento emprendimiento = emprendimientoRepository.getById(idEmprendimiento);
        Voto votoNuevo = new Voto();
        votoNuevo.setGeneradoPor(voto.getGeneradoPor());
        votoNuevo.setIdVoto(voto.getIdVoto());
        votoNuevo.setFechaCreacion(voto.getFechaCreacion());
        votoNuevo.setUsername(usuario);
        votoNuevo.setEvento(evento);
        votoNuevo.setVotoAEmprendimiento(emprendimiento);

//        votoNuevo.setGeneradoPor(voto.getGeneradoPor());//
//        voto.setUsername(modifUsuarioDto.getUsername());
//        voto.setEvento((modifUsuarioDto.getEvento()));
//        voto.setGeneradoPor(modifUsuarioDto.getCreadoPor());
//        voto.setVotoAEmprendimiento(modifUsuarioDto.getVotoAEmprendimiento());

        try {
            votoService.crearVoto(votoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(VotoDto.VotoAVotoDto(votoNuevo));
//            return ResponseEntity.status(HttpStatus.CREATED).body(votoServer.crearVoto(voto, idUsuario, idEvento, idEmprendimiento));

        }catch(Exception x){
            return new ResponseEntity<>("Datos incompletos o erróneos", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> todosLosVotos(){
        List<Voto> votoLista = votoService.allVotos();
        if(!votoLista.isEmpty()){
            List<VotoDto> listaDto = new ArrayList<>();
            for (Voto s: votoLista) listaDto.add(VotoDto.VotoAVotoDto(s));
        return ResponseEntity.ok(listaDto);
        }
        return new ResponseEntity<>("No hay ningun voto", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "buscarPorUsername/{username}")
    public ResponseEntity<?> votosUsername(@PathVariable String username){
        List<Voto> listaUsernameVoto = votoService.buscarVotoPorUsuario(username);
        if(!listaUsernameVoto.isEmpty()){
            List<VotoDto> listaDto = new ArrayList<>();
            for (Voto s: listaUsernameVoto) listaDto.add(VotoDto.VotoAVotoDto(s));
            return new ResponseEntity<>(listaDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("El username no existe o no tiene votos", HttpStatus.NOT_FOUND);
    }


//comente porque estoy probando algo
//    @GetMapping("/{username}")
//    public ResponseEntity<?> contulaPorUsuario(@PathVariable String username){
//        if(!votoServer.votosUsername(username).isEmpty()){
//            return ResponseEntity.ok(votoServer.votosUsername(username));
////        }else return ResponseEntity.notFound().build();
//        }else return new ResponseEntity<>("No se encuentra votos del usuario ingresado o ingreso un formato no valido", HttpStatus.NOT_FOUND);
//    }
}
