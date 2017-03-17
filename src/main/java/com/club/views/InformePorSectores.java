/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.views;

import com.club.BEANS.Sectores;
import com.club.DAOs.SectorDAO;
import com.club.control.utilidades.LeeProperties;
import com.club.tableModels.SectoresTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author dnoble
 */
public class InformePorSectores extends javax.swing.JDialog {

    private SectorDAO sectoresDAO;
    private List<Sectores> listSectoreses;
    private SectoresTableModel tblModel;
    private ListSelectionModel listModelSectoreses;
    private Sectores sectorSelecionado;
    LeeProperties props = new LeeProperties();

    public InformePorSectores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dpDesde.setDate(new Date());
        dpHasta.setDate(new Date());
        DefineModeloTbl();
        buscaTodosLosRegistros();
    }

    private void buscaTodosLosRegistros() {
        listSectoreses.clear();
        sectoresDAO = new SectorDAO();
        listSectoreses.addAll(sectoresDAO.BuscaTodos(Sectores.class));
        tblModel.fireTableDataChanged();
    }

    private void DefineModeloTbl() {

        ((DefaultTableCellRenderer) tblSectores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listSectoreses = new ArrayList<>();
        tblModel = new SectoresTableModel(listSectoreses);
        tblSectores.setModel(tblModel);

        listModelSectoreses = tblSectores.getSelectionModel();
        listModelSectoreses.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (tblSectores.getSelectedRow() != -1) {

                        sectorSelecionado = listSectoreses.get(tblSectores.getSelectedRow());
                    } else {

                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSectores = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnInformeIngresos1 = new org.jasper.viewer.components.JasperRunnerButton();
        btnInformeIngresos2 = new org.jasper.viewer.components.JasperRunnerButton();
        btnInformeIngresos3 = new org.jasper.viewer.components.JasperRunnerButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dpDesde = new org.jdesktop.swingx.JXDatePicker();
        dpHasta = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tblSectores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSectores.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblSectores);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnInformeIngresos1.setText("Generar informe de sectores seleccionados");
        btnInformeIngresos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeIngresos1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnInformeIngresos1, gridBagConstraints);

        btnInformeIngresos2.setText("Balance por Sectores");
        btnInformeIngresos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeIngresos2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnInformeIngresos2, gridBagConstraints);

        btnInformeIngresos3.setText("Generar informe de sectores seleccionados detallado");
        btnInformeIngresos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeIngresos3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnInformeIngresos3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sectores"); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("al"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(jLabel9, gridBagConstraints);

        jLabel17.setText("Período"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(jLabel17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(dpDesde, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(dpHasta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel6, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInformeIngresos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeIngresos1ActionPerformed
        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("desde", dpDesde.getDate());
            parametros.put("hasta", dpHasta.getDate());
            List sectores = new ArrayList();
            int[] selectedRows = tblSectores.getSelectedRows();
            for (int selectedRow : selectedRows) {
                sectores.add(listSectoreses.get(selectedRow).getId());
            }
            parametros.put("sectores", sectores);
            btnInformeIngresos1.setDatabaseDriver(props.getDriver());
            btnInformeIngresos1.setDatabasePassword(props.getPsw());
            btnInformeIngresos1.setDatabaseURL(props.getUrl());
            btnInformeIngresos1.setDatabaseUser(props.getUsr());
            btnInformeIngresos1.setReportParameters(parametros);
            btnInformeIngresos1.setReportURL("/Reportes/informePorSector.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInformeIngresos1ActionPerformed

    private void btnInformeIngresos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeIngresos2ActionPerformed
        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("desde", dpDesde.getDate());
            parametros.put("hasta", dpHasta.getDate());

            btnInformeIngresos2.setDatabaseDriver(props.getDriver());
            btnInformeIngresos2.setDatabasePassword(props.getPsw());
            btnInformeIngresos2.setDatabaseURL(props.getUrl());
            btnInformeIngresos2.setDatabaseUser(props.getUsr());
            btnInformeIngresos2.setReportParameters(parametros);
            btnInformeIngresos2.setReportURL("/Reportes/balancePorSectores.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInformeIngresos2ActionPerformed

    private void btnInformeIngresos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeIngresos3ActionPerformed
        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("desde", dpDesde.getDate());
            parametros.put("hasta", dpHasta.getDate());
            List sectores = new ArrayList();
            int[] selectedRows = tblSectores.getSelectedRows();
            for (int selectedRow : selectedRows) {
                sectores.add(listSectoreses.get(selectedRow).getId());
            }
            parametros.put("sectores", sectores);

            btnInformeIngresos3.setDatabaseDriver(props.getDriver());
            btnInformeIngresos3.setDatabasePassword(props.getPsw());
            btnInformeIngresos3.setDatabaseURL(props.getUrl());
            btnInformeIngresos3.setDatabaseUser(props.getUsr());

            btnInformeIngresos3.setReportParameters(parametros);
            btnInformeIngresos3.setReportURL("/Reportes/informePorSectorDetallado.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInformeIngresos3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InformePorSectores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformePorSectores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformePorSectores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformePorSectores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InformePorSectores dialog = new InformePorSectores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jasper.viewer.components.JasperRunnerButton btnInformeIngresos1;
    private org.jasper.viewer.components.JasperRunnerButton btnInformeIngresos2;
    private org.jasper.viewer.components.JasperRunnerButton btnInformeIngresos3;
    private org.jdesktop.swingx.JXDatePicker dpDesde;
    private org.jdesktop.swingx.JXDatePicker dpHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSectores;
    // End of variables declaration//GEN-END:variables
}
