package com.actividadFinal.ModuloJava2021.entity;

import com.actividadFinal.ModuloJava2021.enums.EstadoEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "evento")
public class Evento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "idEvento")
    private Long idEvento;

//    puede ser una entidad
    @Getter @Setter
    @Column(name = "detallesEvento", length = 500, nullable = false)
    @NotNull(message = "Los detalles del evento no debe ser nulo") @NotBlank(message = "Los detalles del evento no debe estar en blanco")
    private String detallesEvento;

    @Getter @Setter
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;

    @Getter @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fechaCierre")
    private Date fechaCierre;

    @Getter @Setter @Column(name = "estado", nullable = false)
//    @NotNull(message = "El estado del evento no debe ser nulo") @NotBlank(message = "El estado del evento no debe estar en blanco")
    @Enumerated(EnumType.STRING)
    private EstadoEvento estadoEvento;


//    @JoinColumn(name = "id")
//    @MapKeyColumn(name = "emprendimiento_nombre")
//    @ManyToMany
//    private Collection<Emprendimiento> suscriptores;

    @Getter @Setter @Column(name = "premio", nullable = false)
//    @NotNull(message = "El premio del evento no debe ser nulo") @NotBlank(message = "El premio del evento no debe estar en blanco")
    private BigInteger premio;

//    @Getter @Setter
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "votoEnEvento", nullable = false)
//    private List<Voto> votosEvento;


    public Evento(String detallesEvento, Date fechaCierre, EstadoEvento estadoEvento, BigInteger premio, Collection<Emprendimiento> suscriptores, List<Voto> votosEvento){
        this.detallesEvento = detallesEvento;
        this.fechaCierre = fechaCierre;
        this.estadoEvento = estadoEvento;
//        this.suscriptores = suscriptores;
        this.premio = premio;
//        this.votosEvento = votosEvento;
    }

    public Evento() {
    }
}
