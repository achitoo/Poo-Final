package com.servifabrik.servifabrik.actions;

import org.openxava.actions.ViewBaseAction;
import org.openxava.view.View;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;


public class RecalcularTotalPedidoAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        // Vista actual
        View view = getView();

        // OJO: el nombre "detallePedido" debe coincidir con el nombre de la colección en tu entidad Pedido
        @SuppressWarnings("unchecked")
        Collection<Map<String, Object>> detalles =
                (Collection<Map<String, Object>>) view.getValue("detallePedido");

        if (detalles == null || detalles.isEmpty()) {
            addMessage("No hay detalles de pedido para recalcular");
            return;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Map<String, Object> detalle : detalles) {
            // OJO: "subtotal" debe ser EXACTAMENTE el nombre del property en DetallePedido
            Object sub = detalle.get("subtotal");
            if (sub instanceof BigDecimal) {
                total = total.add((BigDecimal) sub);
            }
        }

        // OJO: "totalPedido" debe ser el nombre del property en tu entidad Pedido
        view.setValue("totalPedido", total);

        addMessage("Total del pedido recalculado correctamente");
    }
}

