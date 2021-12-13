package com.actividadFinal.ModuloJava2021.DTOS;

import com.actividadFinal.ModuloJava2021.models.Emprendimiento;
import com.actividadFinal.ModuloJava2021.models.Voto;
import com.actividadFinal.ModuloJava2021.server.VotoServer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

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
    public static VotoServer votoServer;

    public EventoRankingDto(){}
    public void EventoRankingDto(int idEvento){
        List<Voto> votoList = votoServer.buscarVotoPorEvento((long) idEvento);
        Map<String, Long> map = new HashMap<>();
        Collection<Long> values = map.values();
        Set<String> keySet = map.keySet();

//        map.put(votoList.stream().collect(Collectors.toList()), votoList.stream().count());
    }
    public Map<Emprendimiento, Integer> EventosDto(int idEvento){
        List<Voto> votoList = votoServer.buscarVotoPorEvento((long) idEvento);
        Voto v = new Voto();
        Map<Emprendimiento, Integer> map = new HashMap<>();
        int i = 0;
        for(Voto x: votoList){
            i++;
            map.put(x.getVotoAEmprendimiento(), i);

       }
        return map;
    }
//    public List<Emprendimiento> buscarEmprendPorTag(String tags){
//        List<Emprendimiento> list = new ArrayList<>();
//        for (Emprendimiento x : todosEmprendimientos()) {
//            for (Tags j : x.getTags()) {
//                if(j.getNombre().contains(tags)){
//                    list.add(x);
//                }
//            }
//        }
//        return list;
//    }
}
