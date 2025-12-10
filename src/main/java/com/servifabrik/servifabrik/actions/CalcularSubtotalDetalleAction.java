package com.servifabrik.servifabrik.actions;

import org.openxava.actions.ViewBaseAction;
import org.openxava.view.View;

import java.math.BigDecimal;

public class CalcularSubtotalDetalleAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        View view = getView();

        Object cantidadObj = view.getValue("cantidad");          // <-- CAMBIA si tu propiedad se llama distinto
        Object precioObj   = view.getValue("precioUnitario");    // <-- idem

        if (cantidadObj == null || precioObj == null) {
            addError("Debes ingresar cantidad y precio unitario antes de calcular el subtotal");
            return;
        }

        BigDecimal cantidad;
        if (cantidadObj instanceof BigDecimal) {
            cantidad = (BigDecimal) cantidadObj;
        } else if (cantidadObj instanceof Number) {
            cantidad = BigDecimal.valueOf(((Number) cantidadObj).doubleValue());
        } else {
            addError("Formato de cantidad no válido");
            return;
        }

        BigDecimal precio;
        if (precioObj instanceof BigDecimal) {
            precio = (BigDecimal) precioObj;
        } else if (precioObj instanceof Number) {
            precio = BigDecimal.valueOf(((Number) precioObj).doubleValue());
        } else {
            addError("Formato de precio no válido");
            return;
        }

        BigDecimal subtotal = cantidad.multiply(precio);
        view.setValue("subtotal", subtotal);                    // <-- CAMBIA si tu prop se llama diferente

        addMessage("Subtotal calculado: {0}", subtotal);
    }
}
