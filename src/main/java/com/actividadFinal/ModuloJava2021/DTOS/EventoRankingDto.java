package com.actividadFinal.ModuloJava2021.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class EventoRankingDto implements Serializable {
//    @JsonProperty
//    @Getter @Setter
//    private Long id;
//    @Getter @Setter
//    private Long username;
//    @Getter @Setter
//    private Map<Emprendimiento, Voto> voto;
//    @Getter @Setter
//    private Long evento;
//    @Getter @Setter
//    private long emprendimiento;
//    @Getter @Setter
//    private Long cantVotos;
//
//    public RankingDto(Usuario usuario, Evento evento, Emprendimiento emprendimiento, Long cantVotos, Voto voto){
//        this.username = usuario.getId();
//        this.evento  = evento.getIdEvento();
//        this.emprendimiento = emprendimiento.getIdEmprendimiento();
//    }

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String evento;
    @Getter @Setter
    private Long votos;
    @Getter @Setter
    private String emprendimiento;

    public EventoRankingDto(String evento, String emprendimiento, Long votos){
        this.evento  = evento;
        this.emprendimiento = emprendimiento;
        this.votos = votos;
    }
}
