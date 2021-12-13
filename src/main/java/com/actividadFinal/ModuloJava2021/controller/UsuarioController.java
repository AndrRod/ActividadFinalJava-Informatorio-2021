package com.actividadFinal.ModuloJava2021.controller;


import com.actividadFinal.ModuloJava2021.DTOS.UsuarioDto;
import com.actividadFinal.ModuloJava2021.Exception.ExceptionBadRequests;
import com.actividadFinal.ModuloJava2021.models.Usuario;
import com.actividadFinal.ModuloJava2021.server.UsuarioServer;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServer usuarioServer;

    private UsuarioDto usuarioDto;

    @GetMapping(value = "/")
    public ResponseEntity<?> obtenerTodosUsuarios(){
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) StreamSupport
                .stream(usuarioServer.obtUsuarios().spliterator(), false)
                .collect(Collectors.toList());

        List<UsuarioDto> listaDtosUsuario = new ArrayList<>();
//        if(!usuarios.isEmpty()) {return ResponseEntity.ok(usuarios); //            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseEntity.ok(usuarios));
        if(!usuarios.isEmpty()) {
            for (Usuario s: usuarios) listaDtosUsuario.add(UsuarioDto.UsuarioAUsuarioDto(s));
            return ResponseEntity.ok(listaDtosUsuario);
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> guardarUsuario(@RequestBody @Valid Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioServer.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDto.UsuarioAUsuarioDto(usuario));
    }
    @PutMapping(value = "/{id}")
    ResponseEntity<?> modifiarUsuario(@RequestBody @Valid Usuario usuarioModif,@PathVariable(value = "id") @Valid int idUsuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        Optional<Usuario> usuario = usuarioServer.obtUnUsuarioPorId((long) idUsuario);
        if(!usuario.isPresent()){
            return ResponseEntity.notFound().build();
        }
        usuario.get().setApellido(usuarioModif.getApellido());
        usuario.get().setNombre(usuarioModif.getNombre());
        usuario.get().setEmail(usuarioModif.getEmail());
        usuario.get().setPassword(argon2.hash(1, 1024, 1, usuarioModif.getPassword()));
        usuario.get().setCiudad(usuarioModif.getCiudad());
        usuario.get().setProvincia(usuarioModif.getProvincia());
        usuario.get().setPais(usuarioModif.getPais());
        usuario.get().setTipo(usuarioModif.getTipo());
//        return Respon seEntity.status(HttpStatus.CREATED).body(usuarioServer.guardarUsuario(usuario.get()));
        usuarioServer.guardarUsuario(usuario.get());
        return ResponseEntity.ok(UsuarioDto.UsuarioAUsuarioDto(usuario.get()));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable(value = "id") int idUsuario){
        if(!usuarioServer.obtUnUsuarioPorId((long) idUsuario).isPresent()){
//            return ResponseEntity.notFound().build();
                return new ResponseEntity<>("No se encuentra el usuario por el id ingresado o ingresó un formato valido", HttpStatus.NOT_FOUND);
        }
        usuarioServer.borrarUsuario((long) idUsuario);
        return ResponseEntity.ok("usuario borrado exitosamente");
    }

    @GetMapping(value = "/buscarPorId/{id}")
    public ResponseEntity<?> obtenerUnUsuarioPorId(@PathVariable(value = "id") int id){
        Optional<Usuario> usuario = usuarioServer.obtUnUsuarioPorId((long) id);

        if (!usuario.isPresent()) {//            return ResponseEntity.notFound().build();
            return new ResponseEntity<>("No se encuentra el usuario por el id ingresado o ingreso un formato no valido", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(UsuarioDto.UsuarioAUsuarioDto(usuario.get()));

    }
    @GetMapping(value = "/buscarPorCiudad/{id}")
    public ResponseEntity<?> obtenerUnUsuarioPorCiudad(@PathVariable(value = "id") String ciudad) throws ExceptionBadRequests {
        List<Usuario> usuarioLista = usuarioServer.obtUsuariosPorCiudad(ciudad);
        if(!usuarioLista.isEmpty()) {
            List<UsuarioDto> listaDtosUsuario = new ArrayList<>();
            for (Usuario s: usuarioLista) listaDtosUsuario.add(UsuarioDto.UsuarioAUsuarioDto(s));
            return ResponseEntity.ok(listaDtosUsuario);
        }
//        return ResponseEntity.notFound().build();
        return new ResponseEntity<>("No se encuentra el usuario por la ciudad ingresada o ingresó un formato no valido", HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/buscarPorFechaPost/{id}")
    public ResponseEntity<?> obtenerUnUsuarioPorFechaPost(@PathVariable(value = "id") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha){
        List<Usuario> usuarioLista = usuarioServer.obtUsuariosPorFechaPost(fecha);
        if (!usuarioLista.isEmpty()) {
            List<UsuarioDto> listaDtosUsuario = new ArrayList<>();
            for (Usuario s: usuarioLista) listaDtosUsuario.add(UsuarioDto.UsuarioAUsuarioDto(s));
            return ResponseEntity.ok(listaDtosUsuario);
        }
        //        return ResponseEntity.notFound().build();
            return new ResponseEntity<>("No hay ningun usuario creado posteriormente a la fecha ingresada o ingresó un formato no valido", HttpStatus.NOT_FOUND);
        }
}

