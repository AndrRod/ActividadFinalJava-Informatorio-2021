//package com.actividadFinal.ModuloJava2021.DTOS;
//
//import com.actividadFinal.ModuloJava2021.Enums.VotoGenerado;
//import com.actividadFinal.ModuloJava2021.models.Emprendimiento;
//import com.actividadFinal.ModuloJava2021.models.Evento;
//import com.actividadFinal.ModuloJava2021.models.Usuario;
//import com.actividadFinal.ModuloJava2021.models.Voto;
//import com.actividadFinal.ModuloJava2021.repository.VotoRepository;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Date;
//
//
//public class VotoCreacionDto {
//
//    @Getter @Setter
//    private Long id;
//
//    @Getter @Setter
//    private Usuario username;
//
//    @Getter @Setter
//    private Evento evento;
//
//    @Getter @Setter
//    private Emprendimiento votoAEmprendimiento;
//
//    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @Getter @Setter
//    private Date fechaDeCreacion;
//
//    @Getter @Setter
//    private VotoGenerado creadoPor;
//
//
//    public VotoCreacionDto(Long id, Usuario username, Evento evento, Emprendimiento emprendimiento, Date fechaCreacion, VotoGenerado creadoPor){
//        this.id = id;
//        this.username = username;
//        this.evento = evento;
//        this.votoAEmprendimiento = emprendimiento;
//        this.fechaDeCreacion = fechaCreacion;
//        this.creadoPor=creadoPor;
//    }
//    public VotoCreacionDto(){}
//
//    public static VotoRepository votoRepository;
//    public static Voto votoCreacionDto(Voto s, Evento evento, Emprendimiento votoAEmprendimiento, Usuario username){
//        Voto dto = new Voto();
//        dto.setIdVoto(s.getIdVoto());
//        dto.setGeneradoPor(s.getGeneradoPor());
//        dto.setFechaCreacion(s.getFechaCreacion());
//
//        dto.setUsername(username);
//        dto.setEvento(evento);
//        dto.setVotoAEmprendimiento(votoAEmprendimiento);
//        return dto;
//    }
//}
