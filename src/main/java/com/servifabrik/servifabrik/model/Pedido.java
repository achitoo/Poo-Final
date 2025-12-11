package com.servifabrik.servifabrik.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter @Setter
@View(members =
        " fechaPedido; fechaEntrega;" +
                "cliente, empleado;" +
                "detalles;" +
                "montoTotal"
)
@Tab(properties = "idPedido, fechaPedido, cliente.nombre, empleado.nombre, montoTotal")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "fecha_pedido")
    @Required
    private LocalDate fechaPedido;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre, apellido")
    @Required
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre, apellido")
    private Empleado empleado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @ListProperties("producto.nombreProducto, cantidad, precioUnitario, subtotal")
    private Collection<DetallePedido> detalles;

    @Column(name = "monto_total", precision = 12, scale = 2)
    private java.math.BigDecimal montoTotal;
}

