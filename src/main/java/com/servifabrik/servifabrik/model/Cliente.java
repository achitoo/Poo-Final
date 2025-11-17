package com.servifabrik.servifabrik.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
import org.openxava.annotations.View;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@View(members ="idCliente;" + "nombre ,  apellido;" + "telefono + correo;" + "direccion")
public class Cliente {
    @Id
    private Long id;
    @Column(name = "Nombre", length = 25)
    @Required
    private String nombre;

    @Column(name = "Apellido", length = 25)
    @Required
    private String apellido;

    @Column(name = "Telefono", length = 15)
    private String telefono; // mejor manejarlo como String

    @Column(name = "Correo", length = 50)
    private String correo;

    @Column(name = "Dirreccion", length = 100) // mismo nombre que tu tabla
    private String direccion;
}
