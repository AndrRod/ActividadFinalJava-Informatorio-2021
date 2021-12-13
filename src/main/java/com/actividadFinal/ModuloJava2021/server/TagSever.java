package com.actividadFinal.ModuloJava2021.server;

import com.actividadFinal.ModuloJava2021.models.Tags;
import com.actividadFinal.ModuloJava2021.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class TagSever {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public Tags crearTag(Tags tag){return tagRepository.save(tag);}

    @Transactional
    public void eliminarTag(Long id){tagRepository.deleteById(id);}

    @Transactional
    public Optional<Tags> buscarTagId(Long id){return tagRepository.findById(id);}

}
