package com.actividadFinal.ModuloJava2021.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
//    @Column(name = "idTag")
    private Long idTag;

    @Getter @Setter
    private String nombre;

//    @Getter @Setter
//    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
//    private Collection<Emprendimiento> tags = new HashSet<>();

    public Tags(String nombre){
//            , Collection<Emprendimiento> tags){
        this.nombre = nombre;
//        this.tags = tags;
    }

    public Tags() {

    }


}
