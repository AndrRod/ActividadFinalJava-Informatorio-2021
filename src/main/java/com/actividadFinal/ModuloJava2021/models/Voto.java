package com.actividadFinal.ModuloJava2021.models;

import com.actividadFinal.ModuloJava2021.Enums.VotoGenerado;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVoto")
    @Getter @Setter
    private Long idVoto;

    @Getter @Setter
    @Column(name = "generadoPor", nullable = false)
    private VotoGenerado generadoPor;


    @CreationTimestamp
    @Getter @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;


    @Getter @Setter
    @ManyToOne(optional = false)
    @MapKeyColumn(name = "email")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario username;
//
    @Getter @Setter
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JoinColumn(name = "votoAEmprendimiento")
    private Emprendimiento votoAEmprendimiento;
//
    @Getter @Setter
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JoinColumn(name = "votoEnEvento")
    private Evento evento;

    public Voto(VotoGenerado generadoPor){
//            , Emprendimiento eventoEmprendimiento, Usuario username, Evento evento){
        this.generadoPor = generadoPor;
//        this.username = username;
//        this.emprendimientos = eventoEmprendimiento;
//        this.evento =evento;
    }
    public Voto() {
    }

    public static Voto votoCreacion(Voto s, Evento evento, Emprendimiento votoAEmprendimiento, Usuario username){
        Voto dtos = new Voto();
//        dtos.setIdVoto(s.getIdVoto());
        dtos.setGeneradoPor(s.getGeneradoPor());
//        dtos.setFechaCreacion(s.getFechaCreacion());

        dtos.setUsername(username);
        dtos.setEvento(evento);
        dtos.setVotoAEmprendimiento(votoAEmprendimiento);
        return dtos;}
}
