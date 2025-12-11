package com.servifabrik.servifabrik.model;

import com.servifabrik.servifabrik.enums.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@View(members =
        " factura;" +
                "tipoPago, fechaPago;" +
                "monto"
)
@Tab(properties = "idPago, factura.numeroFactura, tipoPago, fechaPago, monto")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name = "id_pago")
    private Integer idPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "numeroFactura")
    @Required
    private Factura factura;

    @Enumerated(EnumType.STRING)
    @Required
    private MetodoPago metodoPago;

    @Column(name = "fecha_pago")
    @Required
    private LocalDate fechaPago;

    @Column(precision = 12, scale = 2)
    @Required
    private BigDecimal monto;
}

