/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.DAOs;

import com.club.BEANS.Venta;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
public class PagosVentasDAO extends DaoGenerico {

    public List buscaPagosPorVenta(Venta venta) {

        List objectos = null;
        Query query = em.createQuery("from PagosVentas where venta = ?1");
        query.setParameter(1, venta);
        objectos = query.getResultList();

        return objectos;
    }

}
