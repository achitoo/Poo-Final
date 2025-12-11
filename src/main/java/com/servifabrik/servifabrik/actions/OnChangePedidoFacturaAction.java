package com.servifabrik.servifabrik.actions;

import com.servifabrik.servifabrik.model.Pedido;
import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;
import org.openxava.view.View;

import java.math.BigDecimal;

public class OnChangePedidoFacturaAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        View view = getView();

        // El valor de una referencia normalmente es el propio objeto
        Pedido pedido = (Pedido) view.getValue("pedido");

        if (pedido == null) {
            view.setValue("totalFactura", null);
            return;
        }

        // Asegurarnos de tener el pedido "vivo" desde JPA (por si viene como proxy)
        Pedido p = XPersistence.getManager().find(Pedido.class, pedido.getIdPedido());
        if (p == null) {
            addError("No se encontró el pedido seleccionado.");
            return;
        }

        BigDecimal total = p.getMontoTotal();
        view.setValue("totalFactura", total);
    }
}

