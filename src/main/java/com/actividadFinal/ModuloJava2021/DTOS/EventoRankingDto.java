package com.actividadFinal.ModuloJava2021.DTOS;

import com.actividadFinal.ModuloJava2021.models.Voto;
import com.actividadFinal.ModuloJava2021.server.VotoServer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Getter
    @Setter
    private Map<String, Integer> emprendimientoYCantVotos = new HashMap<>();

    @Getter
    @Setter
    private int cantidVotos;

//    @Getter @Setter
//    private Long votos;
//    @Getter @Setter
//    private String emprendimiento;

    public EventoRankingDto(Map<String, Integer> emprendimientoYCantVotos, int cantidVotos) {
//            String evento, String emprendimiento, Long votos){
//        this.evento  = evento;
//        this.emprendimiento = emprendimiento;
//        this.votos = votos;

        this.emprendimientoYCantVotos = emprendimientoYCantVotos;
        this.cantidVotos = cantidVotos;
    }

    public static VotoServer votoServer;

    public EventoRankingDto() {
    }

//    public EventoRankingDto(){}
//    public void EventoRankingDto(int idEvento){
//        List<Voto> votoList = votoServer.buscarVotoPorEvento((long) idEvento);
//        Map<String, Long> map = new HashMap<>();
//        Collection<Long> values = map.values();
//        Set<String> keySet = map.keySet();

    //        map.put(votoList.stream().collect(Collectors.toList()), votoList.stream().count());
//    }
    public static HashMap<String, String> EventosDto(int idEvento) {
        List<Voto> voto = votoServer.buscarVotoPorEvento((long) idEvento);
//        Optional<Evento> evento = eventoServer.buscarEventoId((long) idEvento);
        HashMap<String, String> map = new HashMap<>();

        List<String> emprendimientos = new ArrayList<>();
        for (Voto x : voto) {
            emprendimientos.add(x.getVotoAEmprendimiento().getNombre());
        }
        for (int i = 0; i < emprendimientos.stream().count(); i++) {
            int itera = i;
            map.put(emprendimientos.get(i), "cantidad de votos " + emprendimientos.stream().filter(f -> f.equals(emprendimientos.get(itera))).count());
        }
        return map;
    }
}

//        Voto v = new Voto();

//        EventoRankingDto map = new EventoRankingDto();


//        int i = 0;
//        for(Voto x: votoList){
//            map.emprendimientoYCantVotos.put(x.getVotoAEmprendimiento().getNombre(), x.getIdVoto().intValue());
//       }
//        int cant = 0;
//        map = votoList.stream().collect(
//                Collectors.toMap(x -> x.getVotoAEmprendimiento().getNombre(), ));
//        return map;
//    }



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
//}
