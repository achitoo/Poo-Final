package com.servifabrik.servifabrik.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
@Getter @Setter
@View(members =
        "idEmpleado;" +
                "nombre, apellido;" +
                "cargo;" +
                "telefono, correo"
)
@Tab(properties = "idEmpleado, nombre, apellido, cargo, telefono, correo")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(length = 25)
    @Required
    private String nombre;

    @Column(length = 25)
    @Required
    private String apellido;

    @Column(length = 30)
    private String cargo;

    @Column(length = 15)
    private String telefono;

    @Column(length = 50)
    private String correo;
}
