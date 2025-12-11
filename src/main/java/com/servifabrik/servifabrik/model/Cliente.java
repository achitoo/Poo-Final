package com.servifabrik.servifabrik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
@Getter @Setter
@View(members =

                "nombre, apellido;" +
                "telefono, correo;" +
                "direccion"
)
@Tab(properties = "idCliente, nombre, apellido, telefono, correo")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(length = 25)
    @Required
    private String nombre;

    @Column(length = 25)
    @Required
    private String apellido;

    @Column(length = 15)
    private String telefono;

    @Column(length = 50)
    private String correo;

    @Column(name = "direccion", length = 100)
    private String direccion;
}
