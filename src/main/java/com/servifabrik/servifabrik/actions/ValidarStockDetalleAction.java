package com.servifabrik.servifabrik.actions;

import com.servifabrik.servifabrik.model.Inventario;
import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;
import org.openxava.view.View;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

public class ValidarStockDetalleAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        View view = getView();
        Object productoObj = view.getValue("producto");    // referencia al producto (ManyToOne)
        Object cantidadObj = view.getValue("cantidad");

        if (productoObj == null || cantidadObj == null) {
            addError("Debes seleccionar un producto y una cantidad");
            return;
        }

        // cantidad que se quiere usar
        BigDecimal cantidad;
        if (cantidadObj instanceof BigDecimal) {
            cantidad = (BigDecimal) cantidadObj;
        } else if (cantidadObj instanceof Number) {
            cantidad = BigDecimal.valueOf(((Number) cantidadObj).doubleValue());
        } else {
            addError("Formato de cantidad no válido");
            return;
        }

        // id del producto (asumiendo que tu entidad Producto tiene propiedad idProducto)
        Integer idProducto = (Integer) getView().getValue("producto.idProducto"); // AJUSTA SI SE LLAMA DISTINTO
        if (idProducto == null) {
            addError("No se pudo obtener el ID del producto para validar el stock");
            return;
        }

        EntityManager em = XPersistence.getManager();

        TypedQuery<Inventario> query = em.createQuery(
                "select i from Inventario i where i.producto.idProducto = :idProd",
                Inventario.class
        );
        query.setParameter("idProd", idProducto);

        Inventario inv;
        try {
            inv = query.getSingleResult();
        } catch (Exception ex) {
            addError("No hay inventario registrado para este producto");
            return;
        }

        BigDecimal stock = BigDecimal.valueOf(inv.getCantidadDisponible()); // AJUSTA getter si es otro nombre

        if (stock.compareTo(cantidad) < 0) {
            addError("Stock insuficiente. Hay {0} unidades disponibles", stock);
        } else {
            addMessage("Stock disponible OK. Hay {0} unidades", stock);
        }
    }
}
