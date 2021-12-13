package com.actividadFinal.ModuloJava2021.server;

import com.actividadFinal.ModuloJava2021.models.Emprendimiento;
import com.actividadFinal.ModuloJava2021.models.Evento;
import com.actividadFinal.ModuloJava2021.models.Usuario;
import com.actividadFinal.ModuloJava2021.models.Voto;
import com.actividadFinal.ModuloJava2021.repository.EmprendimientoRepository;
import com.actividadFinal.ModuloJava2021.repository.EventoRepository;
import com.actividadFinal.ModuloJava2021.repository.UsuarioRepository;
import com.actividadFinal.ModuloJava2021.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoServer {

    @Autowired
    private VotoRepository votoRepository;
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;
    private EmprendimientoRepository emprendimientoRepository;
//    private VotoCreacionDto votoCreacionDto;

//    public Voto crearVoto(Voto voto, Long username, Long evento, Long emprendimiento){
////        Usuario usuarioEnt = (Usuario) votoRepository.findAll().stream().filter(x-> x.getUsername().getEmail().equals(username)).collect(Collectors.toList());
//        Usuario usuarioEnt = (Usuario) votoRepository.findAll().stream().filter(x-> x.getUsername().getId().equals(username)).collect(Collectors.toList());
//        Evento eventoEnt = (Evento) votoRepository.findAll().stream().filter(x-> x.getEvento().getIdEvento().equals(evento)).collect(Collectors.toList());
////        Emprendimiento emprendimientoEnt = (Emprendimiento) emprendimientoRepository.findAll().stream().filter(x-> x.getNombre().equals(emprendimiento)).collect(Collectors.toList());
//        Emprendimiento emprendimientoEnt = (Emprendimiento) votoRepository.findAll().stream().filter(x-> emprendimiento.equals(x.getVotoAEmprendimiento().getIdEmprendimiento())).collect(Collectors.toList());
//        Voto votoNuevo = new Voto();
//        votoNuevo = Voto.votoCreacion(voto, eventoEnt, emprendimientoEnt, usuarioEnt);
//
////        return votoRepository.save(votoNuevo);
//        return votoNuevo;
//    }
    public Voto crearVoto(Voto voto){return votoRepository.save(voto);}

    public List<Voto> allVotos(){return votoRepository.findAll();}

    public List<Voto> buscarVotoPorUsuario(String username){
        return votoRepository.findAll().stream().filter(x-> x.getUsername().getEmail().equals(username)).collect(Collectors.toList());
    }

//    public long votosUsuario(String usuario){return votoRepository.findAll().stream().filter(p -> p.getUsername().equals(usuario)).count();}
//    comente el sgte para probar (sirve)
//    public List<Voto> votosUsername(String usuario){return votoRepository.findAll().stream().filter(p -> {return p.getUsername().equals(usuario);}).collect(Collectors.toList());}


}
