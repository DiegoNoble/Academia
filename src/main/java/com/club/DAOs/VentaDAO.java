/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.DAOs;

import com.club.BEANS.Socio;
import com.club.BEANS.TipoDocumentoEnum;
import com.club.BEANS.Venta;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class VentaDAO extends DaoGenerico {

    public List buscaVentasPorSocio(Socio socio) {

        List objectos = null;
        Query query = em.createQuery("from Venta v where v.socio = ?1");
        query.setParameter(1, socio);
        objectos = query.getResultList();

        return objectos;
    }

    public List buscaVentasPorSocioCredito(Socio socio) {

        List objectos = null;
        Query query = em.createQuery("from Venta v where v.socio = ?1 and v.tipoDocumentoEnum = 'CREDITO'");
        query.setParameter(1, socio);
        objectos = query.getResultList();

        return objectos;
    }

    public List buscaPorNroVenta(String idVenta) throws Exception {

        List objectos = null;
        Query query = em.createQuery("from Venta where idVenta = " + idVenta + "");
        objectos = query.getResultList();

        return objectos;
    }

    public List buscaPorNroVentaVentaCredito(String idVenta) throws Exception {

        List objectos = null;
        Query query = em.createQuery("from Venta where idVenta = " + idVenta + " and tipo = 'CREDITO'");
        objectos = query.getResultList();

        return objectos;
    }

    public void eliminaVenta(Venta venta) throws Exception {

    }

    public List buscaPorCliente(Integer IdCliente) throws Exception {

        List objectos = null;
        Query query = em.createQuery("from Venta where socio = " + IdCliente + "");
        objectos = query.getResultList();

        return objectos;

    }

    public List buscaPorClienteCredito(Integer IdCliente) throws Exception {

        List objectos = null;
        Query query = em.createQuery("from Venta where cliente = " + IdCliente + " and tipo = 'CREDITO'");
        objectos = query.getResultList();

        return objectos;

    }

    public List buscaEntreFechasVentasCredito(Class classe, String fechaInicial, String fechaFinal, String tipo) {

        List objetos = null;
        try {

            Query query = em.createQuery("from " + classe.getName() + " where fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and tipo ='" + tipo + "'");
            objetos = query.getResultList();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer todos los registros" + e);
        }
        return objetos;

    }

}
