package com.servifabrik.servifabrik.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
<<<<<<< HEAD
=======
import org.openxava.annotations.Tab;
>>>>>>> a0ce2c4 (Clases producto y categoria terminadas)
import org.openxava.annotations.View;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@View(members ="idCliente;" + "nombre ,  apellido;" + "telefono + correo;" + "direccion")
<<<<<<< HEAD
=======
@Tab(properties = "idCliente, nombre, apellido, telefono, correo")
>>>>>>> a0ce2c4 (Clases producto y categoria terminadas)
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
<<<<<<< HEAD
    private String telefono; // mejor manejarlo como String
=======
    private String telefono;
>>>>>>> a0ce2c4 (Clases producto y categoria terminadas)

    @Column(name = "Correo", length = 50)
    private String correo;

<<<<<<< HEAD
    @Column(name = "Dirreccion", length = 100) // mismo nombre que tu tabla
=======
    @Column(name = "Dirreccion", length = 100)
>>>>>>> a0ce2c4 (Clases producto y categoria terminadas)
    private String direccion;
}
