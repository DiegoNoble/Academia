/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorSituacion extends DefaultTableCellRenderer {

    private int ColReferencia;

    public TableRendererColorSituacion(int ColReferencia) {
        this.ColReferencia = ColReferencia;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        //setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if (value != null) {

            Date formatoRecibido = (Date) value;
            String toReturn = new SimpleDateFormat("dd/MM/yyyy").format(formatoRecibido);
            super.setValue(toReturn);

            //setBackground(Color.blue);
            setForeground(Color.BLUE);
            setBorder(new LineBorder(Color.BLUE));

        } else if (value == null) {
            //super.setBackground(Color.red);
            setForeground(Color.red);

        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
