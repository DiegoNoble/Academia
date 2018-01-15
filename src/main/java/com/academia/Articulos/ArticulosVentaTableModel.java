/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academia.Articulos;

import com.club.BEANS.Articulo;
import com.club.BEANS.ArticulosVenta;
import com.club.BEANS.Unidad;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class ArticulosVentaTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Pos", "Cod.", "Nombre", "Cantidad", "Unitario", "Total"};
    //lista para a manipulacao do objeto
    private List<ArticulosVenta> list;
    JTextField txtTotal;

    public ArticulosVentaTableModel(List<ArticulosVenta> list) {
        this.list = list;
    }

    public ArticulosVentaTableModel(List<ArticulosVenta> list, JTextField txtTotal) {
        this.txtTotal = txtTotal;
        this.list = list;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return list.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArticulosVenta c = list.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return c.getPosicion();
            case 1:
                return c.getArticulo().getId();
            case 2:
                if (c.getArticulo() != null) {
                    return c.getArticulo().getNombre();
                } else {
                    return "";
                }
            case 3:
                return c.getCantidad();
            case 4:
                return c.getValor();
            case 5:
                return c.getValor() * c.getCantidad();

            default:
                return null;
        }
    }

    //determina o nome das colunas
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    //determina que tipo de objeto cada coluna irï¿½ suportar
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void agregar(ArticulosVenta ArticulosVentas) {
        list.add(ArticulosVentas);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
        CalculaTotal();
    }

    public void agregar(List<ArticulosVenta> ArticulosVentas) {
        list.clear();

        list.addAll(ArticulosVentas);

        this.fireTableDataChanged();
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
        CalculaTotal();
    }

    public void atualizar(int row, ArticulosVenta ArticulosVentas) {
        list.set(row, ArticulosVentas);
        this.fireTableRowsUpdated(row, row);
        CalculaTotal();
    }

    public ArticulosVenta getCliente(int row) {
        return list.get(row);
    }

    public void CalculaTotal() {
        Double total = 0.0;
        for (ArticulosVenta ArticulosVenta : list) {
            if (ArticulosVenta.getValor() != null) {
                total = total + ArticulosVenta.getValor() * ArticulosVenta.getCantidad();

            }

        }
        //BigDecimal to = new BigDecimal

        //DecimalFormat formato = new DecimalFormat("#,##");
        //formato.setRoundingMode(RoundingMode.CEILING);
        txtTotal.setText(String.format("%.2f", total));

    }
}
