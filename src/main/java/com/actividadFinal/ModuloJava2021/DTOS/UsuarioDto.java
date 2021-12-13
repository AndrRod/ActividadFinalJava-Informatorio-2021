package com.actividadFinal.ModuloJava2021.DTOS;

import com.actividadFinal.ModuloJava2021.Enums.TipoUsuario;
import com.actividadFinal.ModuloJava2021.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class UsuarioDto implements Serializable {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombreYApellido;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String ubicacion;
    @Getter @Setter
    private TipoUsuario tipo;
    @Getter @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;


    public UsuarioDto(Long id, String nombreYApellido, String email, String ubicacion, TipoUsuario tipo, Date fechaCreacion){
        this.id = id;
        this.nombreYApellido = nombreYApellido;
        this.email = email;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioDto(){}

    public static UsuarioDto UsuarioAUsuarioDto(Usuario s){
            UsuarioDto dto = new UsuarioDto();
            dto.setId(s.getId());
            dto.setFechaCreacion(s.getFechaCreacion());
            dto.setNombreYApellido(s.getNombre()+ ' ' + s.getApellido());
            dto.setEmail(s.getEmail());
            dto.setUbicacion("Pais: " + s.getPais() + ", Provincia: " + s.getProvincia() + " y ciudad: " + s.getCiudad() );
            dto.setTipo(s.getTipo());
            return dto;
      }
}
