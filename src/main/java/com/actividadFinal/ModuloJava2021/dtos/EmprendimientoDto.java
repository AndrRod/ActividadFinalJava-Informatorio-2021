package com.actividadFinal.ModuloJava2021.dtos;

import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Collection;

public class EmprendimientoDto {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String contenido;
    @Getter @Setter
    private BigInteger recaudacion;
    @Getter @Setter
    private Boolean publicado;
    @Getter @Setter
    private Collection tags;
    @Getter @Setter
    private Collection urls;

    public EmprendimientoDto(Long id, String nombre, String descripcion, String contenido, BigInteger recaudacion, Boolean publicado, Collection tags, Collection urls){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.recaudacion = recaudacion;
        this.publicado = publicado;
        this.tags = tags;
        this.urls = urls;
    }
    public EmprendimientoDto(){}
    public static EmprendimientoDto EmprendimientoAEmpDto(Emprendimiento s){
        EmprendimientoDto dto = new EmprendimientoDto();
        dto.setId(s.getIdEmprendimiento());
        dto.setNombre(s.getNombre());
        dto.setContenido(s.getContenido());
        dto.setDescripcion(s.getDescripcion());
        dto.setRecaudacion(s.getRecaudacion());
        dto.setPublicado(s.getPublicado());
        dto.setTags(s.getTags());
        dto.setUrls(s.getUrls());
//        dto.setUbicacion("Pais: " + s.getPais() + ", Provincia: " + s.getProvincia() + " y ciudad: " + s.getCiudad() );

        return dto;
    }
}
