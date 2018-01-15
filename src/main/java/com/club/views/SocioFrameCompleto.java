package com.club.views;

import com.club.BEANS.Socio;
import com.club.DAOs.CategoriaDAO;
import com.club.DAOs.SocioDAO;
import com.club.control.utilidades.LeeProperties;
import com.club.huellas.BioMini;
import com.club.tableModels.SocioTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.prompt.PromptSupport;

public final class SocioFrameCompleto extends javax.swing.JInternalFrame {

    private DependienteFrameCompleto seteaSocioTitular;
    private SocioDAO socioDAO;
    private List<Socio> listSocios;
    private SocioTableModel tblModel;
    private ListSelectionModel listModelSocios;
    private Socio socioSeleccionado;
    private HashMap parametros;
    BioMini bioMini;
    LeeProperties props = new LeeProperties();

    public SocioFrameCompleto() {
        initComponents();

        btnSeleccionaTitular.setVisible(false);
        btnEliminar.setVisible(false);
        parametros = new HashMap();
        DefineModeloTbl();
        buscaTodosLosRegistros();
        PromptSupport.setPrompt("Procurar por código, nome ou identidade", txtFiltro);
        btnCarneSocio.setVisible(false);

    }

    public SocioFrameCompleto(Boolean consulta) {
        initComponents();

        btnSeleccionaTitular.setVisible(false);
        btnEliminar.setVisible(false);
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCarneSocio.setVisible(false);

        parametros = new HashMap();
        DefineModeloTbl();
        buscaTodosLosRegistros();
        PromptSupport.setPrompt("Procurar por código, nome ou identidade", txtFiltro);

    }

    public SocioFrameCompleto(DependienteFrameCompleto dependienteFrame) {
        initComponents();

        btnSeleccionaTitular.setVisible(true);
        btnEliminar.setVisible(false);
        btnCarneSocio.setVisible(false);
        btnCarneSocio.setVisible(false);

        DefineModeloTbl();
        buscaTodosLosRegistros();
        this.seteaSocioTitular = dependienteFrame;

    }

    public void buscaTodosLosRegistros() {
        socioDAO = new SocioDAO();
        listSocios.clear();
        listSocios.addAll(socioDAO.BuscaTodos(Socio.class));
        tblModel.fireTableDataChanged();
    }

    private void DefineModeloTbl() {

        ((DefaultTableCellRenderer) tblSocio.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listSocios = new ArrayList<>();
        tblModel = new SocioTableModel(listSocios);
        tblSocio.setModel(tblModel);
        tblSocio.setRowHeight(20);
        listModelSocios = tblSocio.getSelectionModel();
        listModelSocios.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (tblSocio.getSelectedRow() != -1) {

                        socioSeleccionado = listSocios.get(tblSocio.getSelectedRow());
                    }
                }
            }
        });
        tblSocio.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    DetalleSocio detalleSocio = new DetalleSocio(null, true, socioSeleccionado, SocioFrameCompleto.this);
                    detalleSocio.setVisible(true);
                    detalleSocio.toFront();
                }
            }
        });

    }

    private void filtros() {

        socioDAO = new SocioDAO();
        listSocios.clear();
        listSocios.addAll(socioDAO.FiltroInteligenteSocios(txtFiltro.getText()));
        tblModel.fireTableDataChanged();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSocio = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnSeleccionaTitular = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCarneSocio = new org.jasper.viewer.components.JasperRunnerButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Socios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1100, 700));
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Socios"); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        tblSocio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSocio.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblSocio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(txtFiltro, gridBagConstraints);

        btnBuscar.setText("Procurar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(btnBuscar, gridBagConstraints);

        jPanel2.add(jPanel7, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSeleccionaTitular.setText("Seleccionar Titular"); // NOI18N
        btnSeleccionaTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionaTitularActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnSeleccionaTitular, gridBagConstraints);

        btnNuevo.setText("Novo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnNuevo, gridBagConstraints);

        btnEditar.setText("Editar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnEditar, gridBagConstraints);

        btnEliminar.setText("Eliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnEliminar, gridBagConstraints);

        btnCarneSocio.setText("Carne de Socio");
        btnCarneSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarneSocioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnCarneSocio, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        filtros();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        DetalleSocio detalleSocio = new DetalleSocio(null, true, this);
        detalleSocio.setVisible(true);
        detalleSocio.toFront();


    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if (tblSocio.getSelectedRow() != -1) {
            DetalleSocio detalleSocio = new DetalleSocio(null, true, socioSeleccionado, this);
            detalleSocio.setVisible(true);
            detalleSocio.toFront();

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Socio en la tabla", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        try {
            if (tblSocio.getSelectedRow() != -1) {
                int respuesta = JOptionPane.showConfirmDialog(this, "Esta seguro que desea excluir al Socio?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {

                    socioDAO = new SocioDAO();
                    socioSeleccionado = listSocios.get(tblSocio.getSelectedRow());
                    socioDAO.elminiar(Socio.class, socioSeleccionado);

                }
            } else {

                buscaTodosLosRegistros();

                JOptionPane.showMessageDialog(this, "Seleccione un Socio en la tabla", "Atención", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No fue posible ejecutar el SQL deseado" + e);
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSeleccionaTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionaTitularActionPerformed

        if (tblSocio.getSelectedRow() != -1) {

            seteaSocioTitular.setSocioTitular(listSocios.get(tblSocio.getSelectedRow()));

            this.dispose();
            seteaSocioTitular.toFront();

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Socio en la tabla", "Atención", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btnSeleccionaTitularActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed

        filtros();

    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnCarneSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarneSocioActionPerformed

        try {
            parametros.clear();
            parametros.put("fotoCarne", socioSeleccionado.getFoto());
            parametros.put("logoClub", "Imagenes/Escudo.jpg");
            parametros.put("idSocio", socioSeleccionado.getId());
            btnCarneSocio.setDatabaseDriver(props.getDriver());
            btnCarneSocio.setDatabasePassword(props.getPsw());
            btnCarneSocio.setDatabaseURL(props.getUrl());
            btnCarneSocio.setDatabaseUser(props.getUsr());

            btnCarneSocio.setReportParameters(parametros);
            btnCarneSocio.setReportURL("/Reportes/carneSocioOLD.jasper");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar reporte " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCarneSocioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private org.jasper.viewer.components.JasperRunnerButton btnCarneSocio;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSeleccionaTitular;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSocio;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
