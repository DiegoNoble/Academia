/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.Renderers;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorSaldo extends DefaultTableCellRenderer {

    private int ColReferencia;

    public TableRendererColorSaldo(int ColReferencia) {
        this.ColReferencia = ColReferencia;
        setHorizontalAlignment(CENTER);
    }

    @Override
    protected void setValue(Object value) {
        if (value != null) {
            Double valor = Double.valueOf(value.toString());
            if (valor == 0.0) {
                super.setValue(valor);
                setBorder(new LineBorder(Color.BLUE));
                setForeground(Color.BLUE);
            } else {
                super.setValue(valor);
                setBorder(new LineBorder(Color.red));
                setForeground(Color.red);
            }

        } else {

            super.setValue("--");
        }
    }

}
