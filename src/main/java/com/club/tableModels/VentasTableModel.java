/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.tableModels;

import com.club.BEANS.TipoDocumentoEnum;
import com.club.BEANS.Venta;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class VentasTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Data", "Tipo", "Total", "Saldo"};
    //lista para a manipulacao do objeto
    private List<Venta> listVenta;

    public VentasTableModel() {
        listVenta = new LinkedList<Venta>();
    }

    public VentasTableModel(List<Venta> listVenta) {
        this.listVenta = listVenta;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listVenta.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta c = listVenta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFecha();
            case 1:
                return c.getTipoDocumentoEnum();
            case 2:
                return c.getTotal();
            case 3:
                return c.getSaldo();
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
                return Date.class;
            case 1:
                return TipoDocumentoEnum.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

    public void agregar(Venta venta) {
        listVenta.add(venta);

        this.fireTableRowsInserted(listVenta.size() - 1, listVenta.size() - 1);
    }

    public void eliminar(int row) {
        listVenta.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Venta venta) {
        listVenta.set(row, venta);
        this.fireTableRowsUpdated(row, row);
    }

    public Venta getVenta(int row) {
        return listVenta.get(row);
    }

}
