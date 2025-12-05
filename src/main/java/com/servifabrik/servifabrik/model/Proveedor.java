package com.servifabrik.servifabrik.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.util.Collection;

@Entity
@Getter @Setter
@View(members =
        "idProveedor;" +
                "nombreProveedor;" +
                "telefono, correo;" +
                "direccion"
)
@Tab(properties = "idProveedor, nombreProveedor, telefono, correo")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre_proveedor", length = 60)
    @Required
    private String nombreProveedor;

    @Column(length = 15)
    private String telefono;

    @Column(length = 50)
    private String correo;

    @Column(length = 100)
    private String direccion;

    // Relación 1 -> N con Producto
    @OneToMany(mappedBy = "proveedor")
    private Collection<Producto> productos;

}


