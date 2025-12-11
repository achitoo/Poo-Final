package com.servifabrik.servifabrik.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@View(members =

                "nombreCategoria, descripcion"
)
@Tab(properties = "idCategoria, nombreCategoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(length = 40)
    @Required
    private String nombreCategoria;

    @Column(length = 200)
    private String descripcion;

    // SIN @Hidden AQUÍ
    @OneToMany(mappedBy = "categoria")
    private Collection<Producto> productos;
}


