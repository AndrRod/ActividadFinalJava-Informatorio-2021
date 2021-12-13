package com.actividadFinal.ModuloJava2021.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "idUrl")
    private Long id;

    @Getter @Setter
    private String name;

//    @Getter @Setter
//    @ManyToMany(mappedBy = "urls", cascade = CascadeType.ALL)
//    private Collection<Emprendimiento> emprendimiento = new HashSet();

    public Url(String name){
//            , Collection<Emprendimiento> emprendimientos){
        this.name = name;
//        this.emprendimiento = emprendimientos;
    }

    public Url() {

    }
//    public void agregarEmprendimiento(Emprendimiento EmpIngresado){
//        if(emprendimiento == null) emprendimiento = new ArrayList<>();
//        emprendimiento.add(EmpIngresado);
//        EmpIngresado.setUrls((Collection<Url>) this);
//    }
//    public void removerUrl(Emprendimiento EmpIngresado) {
//        emprendimiento.remove(EmpIngresado);
//        EmpIngresado.setUrls(null);
//    }
}
