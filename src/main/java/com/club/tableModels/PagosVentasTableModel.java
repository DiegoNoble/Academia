/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.tableModels;

import com.club.BEANS.PagosVentas;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class PagosVentasTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Data pagamento", "Valor"};
    //lista para a manipulacao do objeto
    private List<PagosVentas> listPagosVentas;

    public PagosVentasTableModel() {
        listPagosVentas = new LinkedList<PagosVentas>();
    }

    public PagosVentasTableModel(List<PagosVentas> listPagosVentas) {
        this.listPagosVentas = listPagosVentas;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listPagosVentas.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PagosVentas c = listPagosVentas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFecha();
            case 1:
                return c.getValor();
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
                return Double.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

    public void agregar(PagosVentas PagosVentas) {
        listPagosVentas.add(PagosVentas);

        this.fireTableRowsInserted(listPagosVentas.size() - 1, listPagosVentas.size() - 1);
    }

    public void eliminar(int row) {
        listPagosVentas.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, PagosVentas PagosVentas) {
        listPagosVentas.set(row, PagosVentas);
        this.fireTableRowsUpdated(row, row);
    }

    public PagosVentas getPagosVentas(int row) {
        return listPagosVentas.get(row);
    }

}
