package com.actividadFinal.ModuloJava2021.entity;

import com.actividadFinal.ModuloJava2021.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Getter @Setter @Column(name = "nombre", nullable = false, length = 50)
    @NotNull(message = " no debe ser nulo") @NotBlank(message = " no debe estar en blanco")
    private String nombre;


    @Getter @Setter @Column(name = "apellido", nullable = false, length = 50)
    @NotNull(message = "no debe ser nulo") @NotBlank(message = " no debe estar en blanco")
    private String apellido;


    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message="no cumple con los requisitos")
    @Getter @Setter @Column(unique = true, name = "email")
    @NotNull(message = "no debe ser nulo") @NotBlank(message = "no debe estar en blanco")
    private String email;

//    @JsonIgnore
//    @Getter(onMethod = @__( @JsonIgnore))
    @Getter @Setter @Column(name = "password", nullable = false)
    @NotNull(message = "no debe ser nulo") @NotBlank(message = "no debe estar en blanco")
    private  String password;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "ciudad", nullable = false)
    @NotNull(message = "no debe ser nulo") @NotBlank(message = "no debe estar en blanco")
    private String ciudad;

    @Getter @Setter @Column(name = "provincia", nullable = false)
    @NotNull(message = "no debe ser nulo") @NotBlank(message = "no debe estar en blanco")
    private String provincia;

    @Getter @Setter @Column(name = "pais", nullable = false)
    @NotNull(message = "no debe ser nulo") @NotBlank(message = "no debe estar en blanco")
    private String pais;


    @Getter @Setter @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

//    @Getter(onMethod = @__( @JsonIgnore)) @Setter
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "username", nullable = false)
//    private List<Voto> votosUsuario;

    public Usuario ( String nom, String ape, String email, String ciudad, String provincia, String pais, TipoUsuario tipo, String password, List<Voto> idUsuarioVoto){
        super();
        this.nombre = nom;
        this.apellido = ape;
        this.email = email;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipo = tipo;
        this.password = password;
//        this.votosUsuario = idUsuarioVoto;
    }
    public Usuario() {
    }
}

