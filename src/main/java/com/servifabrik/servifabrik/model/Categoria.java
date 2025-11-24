package com.servifabrik.servifabrik.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.util.Collection;

    @Entity
    @Getter @Setter
    @View(members =
            "idCategoria;" +
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

        @OneToMany(mappedBy = "categoria")
        @Hidden
        private Collection<Producto> productos;
    }


