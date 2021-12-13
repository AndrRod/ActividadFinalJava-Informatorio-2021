package com.actividadFinal.ModuloJava2021.server;

import com.actividadFinal.ModuloJava2021.models.Usuario;
import com.actividadFinal.ModuloJava2021.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServer {

    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @Transactional(readOnly = true)
    public Iterable<Usuario> obtUsuarios(){return usuarioRepository.findAll();}

    @Transactional(readOnly = true)
    public Optional<Usuario> obtUnUsuarioPorId(Long id) {return  usuarioRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Usuario> obtUsuariosPorCiudad(String ciudad){return usuarioRepository.findAll().stream().filter(p -> {return p.getCiudad().equalsIgnoreCase(ciudad);
    }).collect(Collectors.toList());}

//    @Transactional(readOnly = true)
//    public List<Usuario> obtUsuariosPorFechaPost(Date fecha){return usuarioRepository.findAll().stream().filter(p -> {return p.getFechaCreacion().before(fecha);
//    }).collect(Collectors.toList());}

    @Transactional(readOnly = true)
    public List<Usuario> obtUsuariosPorFechaPost(Date fecha){return usuarioRepository.findAll().stream().filter(p -> {return p.getFechaCreacion().after(fecha);
    }).collect(Collectors.toList());}

    @Transactional
    public Usuario guardarUsuario(Usuario usuario){return usuarioRepository.save(usuario);}

    @Transactional
    public void borrarUsuario(Long id){usuarioRepository.deleteById(id);}


}
