package com.servifabrik.servifabrik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@View(members =

                "producto, proveedor;" +
                "cantidadDisponible, costoUnitario;" +
                "fechaIngreso, ubicacion"
)
@Tab(properties = "idInventario, producto.nombreProducto, proveedor.nombreProveedor, cantidadDisponible, costoUnitario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_inventario")
    private Integer idInventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreProducto")
    @Required
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreProveedor")
    private Proveedor proveedor;

    @Column(name = "cantidad_disponible")
    @Required
    private Integer cantidadDisponible;

    @Column(name = "costo_unitario", precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(length = 50)
    private String ubicacion;
}

