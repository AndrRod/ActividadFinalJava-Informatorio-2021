package com.actividadFinal.ModuloJava2021.server;

import com.actividadFinal.ModuloJava2021.models.Emprendimiento;
import com.actividadFinal.ModuloJava2021.models.Tags;
import com.actividadFinal.ModuloJava2021.repository.EmprendimientoRepository;
import com.actividadFinal.ModuloJava2021.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprendimientoServer {

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public Emprendimiento guardarEmprend(Emprendimiento emprendimiento){return emprendimientoRepository.save(emprendimiento);}

    @Transactional
    public void borrarEmprend(Long id){emprendimientoRepository.deleteById(id);}

    @Transactional
    public List<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}
//    public Iterable<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}

    @Transactional
    public Optional<Emprendimiento> buscarPorIdEmprend(Long id){return emprendimientoRepository.findById(id);}

    @Transactional
    public List<Emprendimiento> buscarEmprendPorTag(String tags){
        List<Emprendimiento> list = new ArrayList<>();
        for (Emprendimiento x : todosEmprendimientos()) {
            for (Tags j : x.getTags()) {
                if(j.getNombre().contains(tags)){
                    list.add(x);
                }
            }
        }
        return list;
    }

    @Transactional
    public List<Emprendimiento> buscarEmpSinPublicar(){
        return emprendimientoRepository.findAll().stream().filter(p -> p.getPublicado().equals(false)).collect(Collectors.toList());
    }


//    esta parte de abajo traje de la entidad tags


    @Transactional
    public Tags crearTag(Tags tag){return tagRepository.save(tag);}

    @Transactional
    public void eliminarTag(Long id){tagRepository.deleteById(id);}

    @Transactional
    public Optional<Tags> buscarTagId(Long id){return tagRepository.findById(id);}

}
