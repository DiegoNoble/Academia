/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.views;

import Utilidades.ControlarEntradaTexto;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class PagoVentaDialog extends javax.swing.JDialog {

    Double valor;
    Double saldo;

    public PagoVentaDialog(java.awt.Frame parent, boolean modal, Double saldo) {
        super(parent, modal);
        initComponents();
        this.saldo = saldo;
        setLocationRelativeTo(null);
        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        txtValor.setDocument(new ControlarEntradaTexto(10, chs));
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {

        this.valor = valor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Digite o valor da entrega");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Valor pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtValor, gridBagConstraints);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmar, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        setValor(Double.valueOf(txtValor.getText()));
        if (saldo < valor) {
            JOptionPane.showMessageDialog(null, "O valor da entrega nao pode superar o saldo", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            this.dispose();
        }

    }//GEN-LAST:event_btnConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
