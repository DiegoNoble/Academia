/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.DAOs;

import com.club.BEANS.ArticulosVenta;
import com.club.BEANS.Venta;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class ArticulosVentaDAO extends DaoGenerico {

    public ArticulosVentaDAO() {

    }

    public List<ArticulosVenta> buscarPorPedido(Venta venta) {

        List objetos = null;
        try {

            Query query = em.createQuery("from  ArticulosPedido where pedido.id = '" + venta.getId() + "'");
            objetos = query.getResultList();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer todos los registros" + e);
        }
        return objetos;
    }

}
