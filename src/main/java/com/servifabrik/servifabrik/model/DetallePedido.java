package com.servifabrik.servifabrik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@Tab(properties = "pedido.idPedido, producto.nombreProducto, cantidad, precioUnitario, subtotal")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_detalle_pedido")
    private Integer idDetallePedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @Required
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreProducto")
    @Required
    private Producto producto;

    @Column
    @Required
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    @Required
    private BigDecimal precioUnitario;

    @Column(precision = 12, scale = 2)
    @Required
    private BigDecimal subtotal;
}

