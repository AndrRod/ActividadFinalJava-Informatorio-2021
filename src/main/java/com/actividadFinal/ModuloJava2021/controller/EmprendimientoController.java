package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.dtos.EmprendimientoDto;
import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.actividadFinal.ModuloJava2021.service.EmprendimientoServer;
import com.actividadFinal.ModuloJava2021.service.TagSever;
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
@RequestMapping("/emprendimiento")
public class EmprendimientoController {

    @Autowired
    private EmprendimientoServer emprendimientoServer;

    @Autowired
    private TagSever tagSever;

    @PostMapping("/")
    public ResponseEntity<?> altaEmprendimiento(@RequestBody @Valid Emprendimiento emprendimiento){
        emprendimientoServer.guardarEmprend(emprendimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmprendimientoDto.EmprendimientoAEmpDto(emprendimiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmprendimientoPorId(@PathVariable int id){
        if(!emprendimientoServer.buscarPorIdEmprend((long)id).isEmpty()){
            emprendimientoServer.borrarEmprend((long)id);
            return ResponseEntity.ok("El emprendimiento fue borrado con exito");
        }
        return new ResponseEntity<>("No se encontro el emprendimiento o se ingreso valor incorrecto", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEmprendimiento(@RequestBody @Valid Emprendimiento emprendModif, @PathVariable(value = "id") int id){
        Optional<Emprendimiento> emp = emprendimientoServer.buscarPorIdEmprend((long)id);
        if(!emp.isPresent()){
            return new ResponseEntity<>("No hay ningún usuario identificado con el id", HttpStatus.NOT_FOUND);
        }
        emp.get().setNombre(emprendModif.getNombre());
        emp.get().setDescripcion(emprendModif.getDescripcion());
        emp.get().setContenido(emprendModif.getContenido());
        emp.get().setRecaudacion(emprendModif.getRecaudacion());
        emp.get().setPublicado(emprendModif.getPublicado());
//        emp.get().getTag(emprendModif.getTag());
        emp.get().setTags(emprendModif.getTags());
        emp.get().setUrls(emprendModif.getUrls());

        emprendimientoServer.guardarEmprend(emp.get())  ;
        return ResponseEntity.ok(EmprendimientoDto.EmprendimientoAEmpDto(emp.get()));
//                new ResponseEntity<>("Usuario modificado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> todosEmpren(){
//        ArrayList<Emprendimiento> emprendimientos = (ArrayList<Emprendimiento>) StreamSupport
//                .stream(emprendimientoServer.todosEmprendimientos().spliterator(), false)
//                .collect(Collectors.toList());
//        if(!emprendimientos.isEmpty()){
        ArrayList<Emprendimiento> emprendimientos = (ArrayList<Emprendimiento>) StreamSupport
                .stream(emprendimientoServer.todosEmprendimientos().spliterator(), false)
                .collect(Collectors.toList());

        if(!emprendimientos.isEmpty()){
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok().body(listaDtosEmp);
        }
        return new ResponseEntity<>("No se encuentra ningún emprendimiento", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarPorTag/{tag}")
    public ResponseEntity<?> buscarPalabra(@PathVariable String tag){
        if(!emprendimientoServer.buscarEmprendPorTag(tag).isEmpty()){
            List<Emprendimiento> emprendimientos = emprendimientoServer.buscarEmprendPorTag(tag);
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok(listaDtosEmp);
        }
        return new ResponseEntity<>("No hay emprendimiento que coincida con su busqueda", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarSinPublicar")
    public ResponseEntity<?> buscarEmprSinPublicar(){
        List<Emprendimiento> emprendimientos = emprendimientoServer.buscarEmpSinPublicar();

        if(!emprendimientoServer.buscarEmpSinPublicar().isEmpty()){
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok(listaDtosEmp);
        }
        return new ResponseEntity<>("No existe emprendimiento sin publicar.", HttpStatus.NOT_FOUND);
    }

}
