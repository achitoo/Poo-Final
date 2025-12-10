package com.servifabrik.servifabrik.actions;

import org.openxava.actions.ViewBaseAction;
import org.openxava.view.View;

import java.math.BigDecimal;

public class CalcularCambioPagoAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        View view = getView();

        Object totalObj  = view.getValue("montoTotal");  // AJUSTA nombres
        Object pagoObj   = view.getValue("montoPago");

        if (totalObj == null || pagoObj == null) {
            addError("Debes indicar el monto total y el pago del cliente");
            return;
        }

        BigDecimal total, pago;

        if (totalObj instanceof BigDecimal) {
            total = (BigDecimal) totalObj;
        } else if (totalObj instanceof Number) {
            total = BigDecimal.valueOf(((Number) totalObj).doubleValue());
        } else {
            addError("Formato de monto total no válido");
            return;
        }

        if (pagoObj instanceof BigDecimal) {
            pago = (BigDecimal) pagoObj;
        } else if (pagoObj instanceof Number) {
            pago = BigDecimal.valueOf(((Number) pagoObj).doubleValue());
        } else {
            addError("Formato de monto de pago no válido");
            return;
        }

        BigDecimal cambio = pago.subtract(total);
        if (cambio.compareTo(BigDecimal.ZERO) < 0) {
            addError("El pago es insuficiente. Falta {0}", cambio.abs());
            return;
        }

        view.setValue("cambio", cambio);  // propiedad cambio en tu entidad Pago
        addMessage("Cambio: {0}", cambio);
    }
}
