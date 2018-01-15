/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.DAOs;

import com.club.BEANS.Articulo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
public class ArticuloDAO extends DaoGenerico {

    public Articulo buscaArtUnicoPorIDStr(String id) throws Exception {

        Articulo articulo = new Articulo();
        Query query = em.createQuery("from Articulos where id ='" + id + "'");
        articulo = (Articulo) query.getSingleResult();

        return articulo;

    }

    public List<String> buscarIdsArticulos() throws Exception {
        List ids = null;
        Query query = em.createQuery("Select id from Articulo");
        ids = query.getResultList();

        return ids;

    }

    public List<String> buscarNombresArticulos() throws Exception {
        List ids = null;
        Query query = em.createQuery("Select nombre from Articulo ");
        ids = query.getResultList();

        return ids;

    }

    public List BuscaTodosPorSituacion(String situacion) {

        List objetos = null;

        try {
            Query query = em.createQuery("from Articulo a where a.activo = '" + situacion + "'");
            objetos = query.getResultList();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar todos" + e);
        }
        return objetos;
    }

    public List<Articulo> BuscarArticulosArtivosAgotados() {

        List objetos = null;

        try {
            Query query = em.createQuery("from Articulo a where a.activo = 'Activo' and a.cantidad <=0");
            objetos = query.getResultList();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar todos" + e);
        }
        return objetos;
    }

}
