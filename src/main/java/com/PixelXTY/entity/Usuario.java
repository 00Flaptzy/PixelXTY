package com.PixelXTY.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Data

@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;

    @Column(unique=true)
    private String email;

    private String password;

    private String rol="USER";
    @OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
    private List<Tarea> tareas;

    @OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
    private List<Horario> horarios;

}
