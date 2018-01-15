/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.views;

import Utilidades.ControlarEntradaTexto;
import Utilidades.Utilidades;
import com.club.BEANS.Categoria;
import com.club.BEANS.Socio;
import com.club.DAOs.CategoriaDAO;
import com.club.DAOs.SocioDAO;
import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author Diego
 */
public class DetalleSocio extends javax.swing.JDialog {

    Socio socioSeleccionado;
    SocioDAO socioDAO;
    SocioFrameCompleto socioFrameCompleto;

    public DetalleSocio(java.awt.Frame parent, boolean modal, Socio socio, SocioFrameCompleto socioFrameCompleto) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.socioSeleccionado = socio;
        this.socioFrameCompleto = socioFrameCompleto;
        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        txtCelular.setDocument(new ControlarEntradaTexto(9, chs));
        cargaComboBox();
        muestraDetalles();
    }

    public DetalleSocio(java.awt.Frame parent, boolean modal, SocioFrameCompleto socioFrameCompleto) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.socioFrameCompleto = socioFrameCompleto;
        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        txtCelular.setDocument(new ControlarEntradaTexto(9, chs));
        cargaComboBox();
        dpFechaIngreso.setDate(new Date());
        dpFechaNacimiento.setDate(new Date());
    }

    private void cargaComboBox() {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> listCategoria = categoriaDAO.BuscaTodos(Categoria.class);
            for (Categoria categoria : listCategoria) {
                cbCategoria.addItem(categoria);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No fue posible cargar categorias y cobradores" + e);
            e.printStackTrace();
        }

    }

    private void muestraDetalles() {

        try {
            jlCodigoSocio.setText(socioSeleccionado.getId().toString());

            dpFechaNacimiento.setDate(socioSeleccionado.getFechanacimiento());
            dpFechaIngreso.setDate(socioSeleccionado.getFechaingreso());
            txtNombre.setText(socioSeleccionado.getNombre());
            ftxtCI.setText(socioSeleccionado.getCi());
            txtNacionalidad.setText(socioSeleccionado.getNacionalidad());
            txtCiudad.setText(socioSeleccionado.getCiudad());
            txtBarrio.setText(socioSeleccionado.getBarrio());
            txtTelefono.setText(socioSeleccionado.getTelefono());
            txtCelular.setText(socioSeleccionado.getCelular());
            txtEmail.setText(socioSeleccionado.getEmail());
            txtDireccion.setText(socioSeleccionado.getDireccion());
            txtEstadoCivil.setText(socioSeleccionado.getEstadocivil());
            txtProfesion.setText(socioSeleccionado.getProfesion());
            cbSituacion.setSelectedItem(socioSeleccionado.getSituacion());
            cbSexo.setSelectedItem(socioSeleccionado.getSexo());
            ftxtCPF.setText(socioSeleccionado.getCpf());

            if (socioSeleccionado.getFoto() != null) {
                Image imagen = new ToolkitImage(new ByteArrayImageSource(socioSeleccionado.getFoto()));
                Image imgReDimensionada = imagen.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                        Image.SCALE_SMOOTH);

                ImageIcon icon = new ImageIcon(imgReDimensionada);

                jlblFoto.setIcon(icon);

            }
            //txtFoto.setText(socioSeleccionado.getFoto());

            cbCategoria.setSelectedItem(socioSeleccionado.getCategoria());
            txtAreaHistorico.setText(socioSeleccionado.getHistoria());

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error al mostrar detalles" + error);
            error.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlCodigoSocio = new javax.swing.JLabel();
        ftxtCI = new javax.swing.JFormattedTextField();
        jlblFoto = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtFoto = new javax.swing.JTextField();
        btnFoto = new javax.swing.JButton();
        dpFechaIngreso = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBarrio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtProfesion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        dpFechaNacimiento = new org.jdesktop.swingx.JXDatePicker();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbSituacion = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        txtAreaHistorico = new java.awt.TextArea();
        cbCategoria = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        ftxtCPF = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 650));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Código"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Nome"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtNombre, gridBagConstraints);

        jLabel6.setText("Data nascimento"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Sexo"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cbSexo, gridBagConstraints);

        jLabel13.setText("Identidade"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel13, gridBagConstraints);

        jLabel14.setText("Data ingreso"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel14, gridBagConstraints);

        jlCodigoSocio.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jlCodigoSocio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftxtCI, gridBagConstraints);

        jlblFoto.setBackground(new java.awt.Color(204, 204, 204));
        jlblFoto.setOpaque(true);
        jlblFoto.setPreferredSize(new java.awt.Dimension(3, 4));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 170;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jlblFoto, gridBagConstraints);

        jLabel21.setText("Foto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel21, gridBagConstraints);

        txtFoto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtFoto, gridBagConstraints);

        btnFoto.setText("...");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnFoto, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(dpFechaIngreso, gridBagConstraints);

        jLabel12.setText("Nacionalidade"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtNacionalidad, gridBagConstraints);

        jLabel9.setText("Cidade"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtCiudad, gridBagConstraints);

        jLabel8.setText("Bairro"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtBarrio, gridBagConstraints);

        jLabel17.setText("Telefone"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtTelefono, gridBagConstraints);

        jLabel18.setText("Email"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel18, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtEmail, gridBagConstraints);

        jLabel5.setText("Endereço"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtDireccion, gridBagConstraints);

        jLabel10.setText("Estado Civil"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtEstadoCivil, gridBagConstraints);

        jLabel11.setText("Profissão"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtProfesion, gridBagConstraints);

        jLabel20.setText("Celular"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel20, gridBagConstraints);

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtCelular, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(dpFechaNacimiento, gridBagConstraints);

        jLabel23.setText("Categoría"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel23, gridBagConstraints);

        jLabel24.setText("Situação"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel24, gridBagConstraints);

        cbSituacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo", "Renuncia" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cbSituacion, gridBagConstraints);

        jLabel25.setText("Observações");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(txtAreaHistorico, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cbCategoria, gridBagConstraints);

        jLabel15.setText("CPF"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftxtCPF, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSalvar.setText("Salvar"); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(btnSalvar, gridBagConstraints);

        btnCancelar.setText("Cancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(btnCancelar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Detalhes do socio"); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

        WebCam webCam = new WebCam(null, true);
        webCam.toFront();
        webCam.setVisible(true);

        Image imgReDimensionada = webCam.getImagen().getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(imgReDimensionada);

        jlblFoto.setIcon(icon);

        txtFoto.setText(webCam.getTempFile().getAbsolutePath());

    }//GEN-LAST:event_btnFotoActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed

    }//GEN-LAST:event_txtCelularActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        if (txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del socio", "Atencion", JOptionPane.INFORMATION_MESSAGE);
            txtNombre.requestFocus();

        }  else if (dpFechaNacimiento.getDate().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la Fecha de Nacimiento del Socio", "Atencion", JOptionPane.INFORMATION_MESSAGE);

        } else if (cbCategoria.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoría", "Atencion", JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (jlCodigoSocio.getText().equals("")) {

                try {

                    Socio socio = new Socio();

                    socio.setNombre(txtNombre.getText());
                    socio.setDireccion(txtDireccion.getText());
                    socio.setFechanacimiento(Utilidades.RecibeDateRetornaDateFormatoBD(dpFechaNacimiento.getDate()));
                    socio.setSexo(cbSexo.getSelectedItem().toString());
                    socio.setEstadocivil(txtEstadoCivil.getText());
                    socio.setProfesion(txtProfesion.getText());
                    socio.setNacionalidad(txtNacionalidad.getText());
                    socio.setCiudad(txtCiudad.getText());
                    socio.setCi(ftxtCI.getText());
                    socio.setCpf(ftxtCPF.getText());
                    socio.setFechaingreso(Utilidades.RecibeDateRetornaDateFormatoBD(dpFechaIngreso.getDate()));
                    socio.setSituacion(cbSituacion.getSelectedItem().toString());
                    socio.setBarrio(txtBarrio.getText());
                    socio.setTelefono(txtTelefono.getText());
                    socio.setCelular(txtCelular.getText());
                    socio.setEmail(txtEmail.getText());
                    socio.setHistoria(txtAreaHistorico.getText());
                    socio.setCategoria((Categoria) cbCategoria.getSelectedItem());

                    if (!txtFoto.getText().equals("")) {
                        File file = new File(txtFoto.getText());
                        byte[] fotoEnFormatoBytes = new byte[(int) file.length()];
                        FileInputStream fileInputStream = new FileInputStream(file);
                        fileInputStream.read(fotoEnFormatoBytes);
                        fileInputStream.close();
                        socio.setFoto(fotoEnFormatoBytes);
                    }

                    socioDAO = new SocioDAO();
                    if ((socioDAO.VerificaCI(ftxtCI.getText())) == true) {

                        JOptionPane.showMessageDialog(null, "ERROR: El socio ya se encuentra en la base de datos!");

                    } else {
                        socioDAO = new SocioDAO();
                        socioDAO.Salvar(socio);
                        JOptionPane.showMessageDialog(null, "Socio registrado correctamente!");
                        socioFrameCompleto.buscaTodosLosRegistros();
                        this.dispose();
                    }

                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "No fue posible salvar el socio" + error);
                    error.printStackTrace();
                }

            } else {  //procedimento realizado cuando se desea alterar un registro

                if (txtNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre del socio", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    txtNombre.requestFocus();

                } else if (ftxtCI.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese la CI del socio", "Atencion", JOptionPane.INFORMATION_MESSAGE);

                } else if (dpFechaNacimiento.getDate().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese la Fecha de Nacimiento del Socio", "Atencion", JOptionPane.INFORMATION_MESSAGE);

                } else if (cbCategoria.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione una categoría", "Atencion", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    try {

                        socioSeleccionado.setId(Integer.parseInt(jlCodigoSocio.getText()));
                        socioSeleccionado.setNombre(txtNombre.getText());
                        socioSeleccionado.setDireccion(txtDireccion.getText());
                        socioSeleccionado.setFechanacimiento(Utilidades.RecibeDateRetornaDateFormatoBD(dpFechaNacimiento.getDate()));
                        socioSeleccionado.setSexo(cbSexo.getSelectedItem().toString());
                        socioSeleccionado.setEstadocivil(txtEstadoCivil.getText());
                        socioSeleccionado.setProfesion(txtProfesion.getText());
                        socioSeleccionado.setNacionalidad(txtNacionalidad.getText());
                        socioSeleccionado.setCiudad(txtCiudad.getText());
                        socioSeleccionado.setCi(ftxtCI.getText());
                        socioSeleccionado.setCpf(ftxtCPF.getText());
                        socioSeleccionado.setFechaingreso(Utilidades.RecibeDateRetornaDateFormatoBD(dpFechaIngreso.getDate()));
                        socioSeleccionado.setSituacion(cbSituacion.getSelectedItem().toString());
                        socioSeleccionado.setBarrio(txtBarrio.getText());
                        socioSeleccionado.setTelefono(txtTelefono.getText());
                        socioSeleccionado.setCelular(txtCelular.getText());
                        socioSeleccionado.setEmail(txtEmail.getText());
                        socioSeleccionado.setHistoria(txtAreaHistorico.getText());
                        socioSeleccionado.setCategoria((Categoria) cbCategoria.getSelectedItem());
                        if (!txtFoto.getText().equals("")) {
                            File file = new File(txtFoto.getText());
                            byte[] fotoEnFormatoBytes = new byte[(int) file.length()];
                            FileInputStream fileInputStream = new FileInputStream(file);
                            fileInputStream.read(fotoEnFormatoBytes);
                            fileInputStream.close();
                            socioSeleccionado.setFoto(fotoEnFormatoBytes);
                        }
                        socioDAO = new SocioDAO();
                        socioDAO.Actualizar(socioSeleccionado);

                        JOptionPane.showMessageDialog(null, "Socio alterado correctamente!");

                        socioFrameCompleto.buscaTodosLosRegistros();
                        this.dispose();
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "No fue posible ejecutar el SQL deseado " + error);
                        error.printStackTrace();
                    }
                }
            }

        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        this.dispose();

    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JComboBox cbSituacion;
    private org.jdesktop.swingx.JXDatePicker dpFechaIngreso;
    private org.jdesktop.swingx.JXDatePicker dpFechaNacimiento;
    private javax.swing.JFormattedTextField ftxtCI;
    private javax.swing.JFormattedTextField ftxtCPF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jlCodigoSocio;
    private javax.swing.JLabel jlblFoto;
    private java.awt.TextArea txtAreaHistorico;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProfesion;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
