package com.servifabrik.servifabrik.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@View(members =
        "idProducto;" +
                "nombreProducto, categoria;" +
                "descripcion;" +
                "precioUnitario"
)
@Tab(properties = "idProducto, nombreProducto, categoria.nombreCategoria, precioUnitario")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(length = 50)
    @Required
    private String nombreProducto;

    @Column(length = 200)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreCategoria")
    @Required
    private Categoria categoria;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    @Required
    private BigDecimal precioUnitario;
}
