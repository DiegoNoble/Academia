/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academia.Articulos;

import com.club.BEANS.CategoriaArticulo;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class CategoriaTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Nombre"};
    //lista para a manipulacao do objeto
    private List<CategoriaArticulo> list;

    public CategoriaTableModel() {
        list = new LinkedList<>();
    }

    public CategoriaTableModel(List<CategoriaArticulo> list) {
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
        CategoriaArticulo c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getNombre();

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
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return true;

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CategoriaArticulo c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                c.setNombre((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregar(CategoriaArticulo ciudad) {
        list.add(ciudad);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, CategoriaArticulo ciudad) {
        list.set(row, ciudad);
        this.fireTableRowsUpdated(row, row);
    }

    public CategoriaArticulo getCliente(int row) {
        return list.get(row);
    }

}
