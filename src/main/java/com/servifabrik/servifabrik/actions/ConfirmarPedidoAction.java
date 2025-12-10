package com.servifabrik.servifabrik.actions;

import com.servifabrik.servifabrik.model.Factura;
import com.servifabrik.servifabrik.model.Pedido;
import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;
import org.openxava.view.View;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConfirmarPedidoAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {

        View view = getView();

        // 1) Obtener el id del pedido que está en la vista
        Integer idPedido = (Integer) view.getValue("idPedido");
        if (idPedido == null) {
            addError("Primero guarda el pedido antes de confirmarlo.");
            return;
        }

        // 2) Buscar el pedido en la base de datos
        Pedido pedido = XPersistence.getManager().find(Pedido.class, idPedido);
        if (pedido == null) {
            addError("No se encontró el pedido en la base de datos.");
            return;
        }

        // 3) Tomar el monto total del pedido
        BigDecimal total = pedido.getMontoTotal();
        if (total == null) {
            addError("El monto total del pedido es nulo. Usa 'Recalcular total' antes de confirmar.");
            return;
        }

        // 4) (Opcional) Podrías validar que tenga detalles
        // if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
        //     addError("El pedido no tiene detalles. Agrega productos antes de confirmarlo.");
        //     return;
        // }

        // 5) Crear la factura asociada al pedido
        Factura factura = new Factura();
        factura.setNumeroFactura("FAC-" + idPedido);   // puedes cambiar la lógica del número
        factura.setPedido(pedido);
        factura.setFechaFactura(LocalDate.now());
        factura.setTotalFactura(total);
        factura.setEstadoFactura("Emitida");

        // 6) Guardar factura
        XPersistence.getManager().persist(factura);

        addMessage("El pedido " + idPedido + " fue confirmado y se generó la factura correctamente.");
    }
}
