package com.servifabrik.servifabrik.actions;

import com.servifabrik.servifabrik.model.Inventario;
import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;
import org.openxava.view.View;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ReponerInventarioAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        View view = getView();
        EntityManager em = XPersistence.getManager();

        Object idObj = view.getValue("idInventario");   // AJUSTA nombre del id
        Object repObj = view.getValue("cantidadAReponer"); // propiedad que uses para indicar la reposición

        if (idObj == null || repObj == null) {
            addError("Debes indicar el registro de inventario y la cantidad a reponer");
            return;
        }

        Integer idInv = (Integer) idObj;
        Inventario inv = em.find(Inventario.class, idInv);

        if (inv == null) {
            addError("No se encontró el inventario");
            return;
        }

        BigDecimal aReponer;
        if (repObj instanceof BigDecimal) {
            aReponer = (BigDecimal) repObj;
        } else if (repObj instanceof Number) {
            aReponer = BigDecimal.valueOf(((Number) repObj).doubleValue());
        } else {
            addError("Formato de cantidad a reponer no válido");
            return;
        }

        int actual = inv.getCantidadDisponible(); // AJUSTA
        inv.setCantidadDisponible(actual + aReponer.intValue());

        em.persist(inv);

        addMessage("Inventario actualizado. Cantidad actual: {0}", inv.getCantidadDisponible());
    }
}
