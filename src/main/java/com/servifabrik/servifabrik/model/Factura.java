package com.servifabrik.servifabrik.model;

import com.servifabrik.servifabrik.enums.EstadoFactura;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@View(members =
        " numeroFactura;" +
                "pedido; fechaFactura;" +
                "totalFactura; estadoFactura"
)
@Tab(properties = "numeroFactura, fechaFactura, pedido.idPedido, totalFactura, estadoFactura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_factura")
    private Integer idFactura;

    @Column(name = "numero_factura", length = 20)
    @Required
    private String numeroFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "idPedido")
    @Required
    private Pedido pedido;

    @Column(name = "fecha_factura")
    @Required
    private LocalDate fechaFactura;

    @Column(name = "total_factura", precision = 12, scale = 2)
    @Required
    private BigDecimal totalFactura;

    @Enumerated(EnumType.STRING)
    @Required
    private EstadoFactura estadoFactura;
}

