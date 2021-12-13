package com.actividadFinal.ModuloJava2021.DTOS;

import com.actividadFinal.ModuloJava2021.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioDtoInterfaz {

    UsuarioDtoInterfaz INSTANCIA= Mappers.getMapper(UsuarioDtoInterfaz.class);
    @Mapping(source = "nombre", target = "nombreYApellido")
    @Mapping(source = "pais", target = "ubicacion")
    UsuarioDto convertirUsuarioADto(Usuario usuario);

//      default UsuarioDto UsuarioAUsuarioDto(Usuario s){
//            UsuarioDto dto = new UsuarioDto();
//            dto.setId(s.getId());
//            dto.setNombreYApellido(s.getNombre()+ ' ' + s.getApellido());
//            dto.setEmail(s.getEmail());
//            dto.setUbicacion("Pais: " + s.getPais() + ", Provincia: " + s.getProvincia() + " y ciudad: " + s.getCiudad() );
//            dto.setTipo(s.getTipo());
//            return dto;
//      }
}
