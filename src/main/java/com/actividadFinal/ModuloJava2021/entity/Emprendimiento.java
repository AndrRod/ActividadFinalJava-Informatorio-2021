package com.actividadFinal.ModuloJava2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "emprendimiento")
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(unique = true, nullable = false, name = "id")
    private long idEmprendimiento;

    @Size(max = 20, message="El nombre del emprendimiento debe tener entre 4 y 20 caracteres")
    @NotNull(message = "El nombre no debe ser nulo") @NotBlank(message = "El nombre no debe estar en blanco")
    @Getter @Setter @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;

    @Getter @Setter @Column(name = "descripcion", length = 250, nullable = false)
    @NotNull(message = "La descripcion no debe ser nula") @NotBlank(message = "La descripcion no debe estar en blanco")
    private String descripcion;

    @NotNull(message = "El contenido no debe ser nulo") @NotBlank(message = "El contenido no debe estar en blanco")
    @Getter @Setter @Column(name = "contenido", length = 500, nullable = false)
    private String contenido;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "recaudacion")
    private BigInteger recaudacion;

//    @Size(min = 0, max = 1)
    @Getter @Setter @Column(name = "publicado", nullable = false)
    private Boolean publicado;

    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "url_nombre")
    private Collection<Url> urls = new HashSet();



//    public void agregarUrl(Url urlIngresado){
//        if(urls == null) urls = new ArrayList<>();
//        urls.add(urlIngresado);
//        urlIngresado.setEmprendimiento((Collection<Emprendimiento>) this);
//    }
//    public void removerUrl(Url removUrl) {
//        urls.remove(removUrl);
//        removUrl.setEmprendimiento(null);
//    }

    // puede ser entidad o embebido
    //    @Getter @Setter @Column(name = "tags")
    //    private String tags;
    @Getter  @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tags")
    private Collection<Tags> tags = new HashSet();

//    @Getter @Setter
////    mappedBy = "idVoto",
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "votoAEmprendimiento", nullable = false)
//    private List<Voto> votoEmprendimiento;

    @Getter @Setter
    @JoinColumn(name = "id")
//    @MapKeyColumn(name = "emprendimiento_nombre")
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Evento> suscriptores = new HashSet<>();


    public Emprendimiento (String nombre, String descripcion, String contenido, BigInteger recaudacion, Boolean publicado, Collection<Url> url, List<Voto> votoEmprendimiento
    , Collection<Tags> tags, Collection<Evento> suscriptores){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.publicado = publicado;
        this.urls = url;
        this.recaudacion = recaudacion;
        this.tags = tags;
//        this.votoEmprendimiento = votoEmprendimiento;
        this.suscriptores = suscriptores;
    }

    public Emprendimiento() {
    }


}
