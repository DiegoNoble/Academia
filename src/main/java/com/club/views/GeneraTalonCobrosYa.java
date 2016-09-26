package com.club.views;

import Utilidades.AjustaColumnas;
import Utilidades.EnviarEmail;
import Utilidades.EnvioTalonCobrosYa;
import com.club.BEANS.CcCobrador;
import com.club.BEANS.Cobrador;
import com.club.BEANS.Mensualidades;
import com.club.BEANS.Parametros;
import com.club.BEANS.Socio;
import com.club.DAOs.CcCobradorDAO;
import com.club.DAOs.MensualidadesAnuladasDAO;
import com.club.DAOs.MensualidadesDAO;
import com.club.DAOs.ParametrosDAO;
import com.club.DAOs.SocioDAO;
import com.club.Renderers.MeDateCellRenderer;
import com.club.Renderers.TableRendererColor;
import com.club.modelos.MensualidadesTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class GeneraTalonCobrosYa extends javax.swing.JInternalFrame {

    MensualidadesDAO mensualidadesDAO;
    ParametrosDAO parametrosDAO;
    SocioDAO socioDAO;
    MensualidadesAnuladasDAO mensualidadesAnuladasDAO;
    CcCobradorDAO ccCobradorDAO;
    Socio socioSeleccionado;
    CcCobrador credito;
    MensualidadesTableModel tblModelMensualidades;
    ListSelectionModel listModelMensualidades;
    Mensualidades mensualidadSeleccionada;
    List<Mensualidades> listMensualidades;
    private static Cobrador cobradorCobrosYa;
    private static Parametros parametros;

    public GeneraTalonCobrosYa() {

        initComponents();
        defineModelo();
        Parametros();
        Calendar venc = Calendar.getInstance();
        venc.set(Calendar.DAY_OF_MONTH, 15);
        dpVencimiento.setDate(venc.getTime());
        muestraContenidoTabla();

    }

    private void Parametros() {
        ParametrosDAO cAO = new ParametrosDAO();
        parametros = (Parametros) cAO.BuscaPorID(Parametros.class, 1);
        cobradorCobrosYa = parametros.getCobradorCobrosYa();
    }

    private void muestraContenidoTabla() {
        Collection<String> situacionRecibo = new ArrayList<String>();
        if (rbPagos.isSelected()) {
            situacionRecibo.add("Pago");
        } else if (rbPendientes.isSelected()) {
            situacionRecibo.add("Pendiente de Pago");
        } else if (rbTodos.isSelected()) {
            situacionRecibo.add("Pendiente de Pago");
            situacionRecibo.add("Pago");
        }
        mensualidadesDAO = new MensualidadesDAO();
        listMensualidades.clear();
        listMensualidades.addAll(mensualidadesDAO.BuscaPorCobradorSituacionVencimiento(cobradorCobrosYa, dpVencimiento.getDate(), situacionRecibo));
        tblModelMensualidades.fireTableDataChanged();
    }

    private void defineModelo() {

        ((DefaultTableCellRenderer) tblMensualidades.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listMensualidades = new ArrayList<>();
        tblModelMensualidades = new MensualidadesTableModel(listMensualidades);
        tblMensualidades.setModel(tblModelMensualidades);

        int[] anchos = {2, 150, 5, 15, 10, 120, 20, 20, 20};
        new AjustaColumnas().ajustar(tblMensualidades, anchos);

        tblMensualidades.getColumn("Pago").setCellRenderer(new TableRendererColor(5));
        tblMensualidades.getColumn("Vencimiento").setCellRenderer(new MeDateCellRenderer());
        //tblMensualidades.getColumn("Fecha Pago").setCellRenderer(new MeDateCellRenderer());

        listModelMensualidades = tblMensualidades.getSelectionModel();
        listModelMensualidades.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (tblMensualidades.getSelectedRow() != -1) {

                        mensualidadSeleccionada = listMensualidades.get(tblMensualidades.getSelectedRow());
                        btnReenviarEmail.setEnabled(true);
                        if (mensualidadSeleccionada.getEnviado() == false) {
                            btnEnviarTalonCobrosYa.setEnabled(true);
                        }
                    }

                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rbPendientes = new javax.swing.JRadioButton();
        rbPagos = new javax.swing.JRadioButton();
        rbTodos = new javax.swing.JRadioButton();
        dpVencimiento = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMensualidades = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnEnviarTalonCobrosYa = new javax.swing.JButton();
        btnReenviarEmail = new javax.swing.JButton();
        btnEnviarTalonesPendientes = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Busqueda por:"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        btnBuscar.setText("Buscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnBuscar, gridBagConstraints);

        buttonGroup1.add(rbPendientes);
        rbPendientes.setSelected(true);
        rbPendientes.setText("Solo pendientes");
        jPanel4.add(rbPendientes);

        buttonGroup1.add(rbPagos);
        rbPagos.setText("Solo pagos");
        jPanel4.add(rbPagos);

        buttonGroup1.add(rbTodos);
        rbTodos.setText("Todos");
        jPanel4.add(rbTodos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jPanel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel2.add(dpVencimiento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consulta de Mensualidades"); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        tblMensualidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMensualidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMensualidadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMensualidades);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel6, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnEnviarTalonCobrosYa.setText("Enviar talón Cobros Ya");
        btnEnviarTalonCobrosYa.setEnabled(false);
        btnEnviarTalonCobrosYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarTalonCobrosYaActionPerformed(evt);
            }
        });
        jPanel5.add(btnEnviarTalonCobrosYa);

        btnReenviarEmail.setText("Re-enviar email");
        btnReenviarEmail.setEnabled(false);
        btnReenviarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReenviarEmailActionPerformed(evt);
            }
        });
        jPanel5.add(btnReenviarEmail);

        btnEnviarTalonesPendientes.setText("Enviar talones pendientes");
        btnEnviarTalonesPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarTalonesPendientesActionPerformed(evt);
            }
        });
        jPanel5.add(btnEnviarTalonesPendientes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        muestraContenidoTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblMensualidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMensualidadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMensualidadesMouseClicked

    private void btnEnviarTalonCobrosYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarTalonCobrosYaActionPerformed

        try {
            EnvioTalonCobrosYa cobrosYa = new EnvioTalonCobrosYa(parametros);
            cobrosYa.enviarTalonMiWeb(socioSeleccionado, mensualidadSeleccionada);

            if (mensualidadSeleccionada.getEnviado() == false) {

                mensualidadSeleccionada.setEnviado(true);
                mensualidadSeleccionada.setFechaHoraTransaccionCobrosYa(new Date());
                mensualidadSeleccionada.setSituacionTalonCobrosYa(cobrosYa.getSituacionTransaccion());
                mensualidadSeleccionada.setNroTalonCobrosYa(cobrosYa.getNroTalonCobrosYa());
                mensualidadSeleccionada.setUrlPDF(cobrosYa.getUrl_pdf());
                mensualidadSeleccionada.setIdSecreto(cobrosYa.getIdSecretoCobrosYa());

                mensualidadesDAO = new MensualidadesDAO();
                mensualidadesDAO.Actualizar(mensualidadSeleccionada);
                JOptionPane.showMessageDialog(this, "Talón: " + mensualidadSeleccionada.getNroTalonCobrosYa() + " creado correctamente!");
                EnviarEmail enviarEmail = new EnviarEmail(cobrosYa.getUrl_pdf(), socioSeleccionado.getEmail());
                Boolean enviaMail = enviarEmail.enviaMail();
                if (enviaMail == true) {

                } else {

                }
            } else {
                JOptionPane.showMessageDialog(this, "El rebibo ya habia sido enviado, nro. talón: " + mensualidadSeleccionada.getNroTalonCobrosYa(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            Logger.getLogger(GeneraTalonCobrosYa.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEnviarTalonCobrosYaActionPerformed

    private void btnReenviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReenviarEmailActionPerformed
        EnviarEmail enviarEmail = new EnviarEmail(mensualidadSeleccionada.getUrlPDF(), socioSeleccionado.getEmail());
        enviarEmail.enviaMail();
    }//GEN-LAST:event_btnReenviarEmailActionPerformed

    private void btnEnviarTalonesPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarTalonesPendientesActionPerformed

        LogMensualidadesCobrosYa log = new LogMensualidadesCobrosYa(null, true, cobradorCobrosYa, parametros, listMensualidades);
        log.setLocationRelativeTo(null);
        log.setVisible(true);
        log.toFront();


    }//GEN-LAST:event_btnEnviarTalonesPendientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEnviarTalonCobrosYa;
    private javax.swing.JButton btnEnviarTalonesPendientes;
    private javax.swing.JButton btnReenviarEmail;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXDatePicker dpVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbPagos;
    private javax.swing.JRadioButton rbPendientes;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblMensualidades;
    // End of variables declaration//GEN-END:variables
}
