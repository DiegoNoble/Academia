package com.academia.Articulos;

import Utilidades.ControlarEntradaTexto;
import com.club.BEANS.Articulo;
import com.club.BEANS.ArticulosVenta;
import com.club.BEANS.Caja;
import com.club.BEANS.MonedaEnum;
import com.club.BEANS.Parametros;
import com.club.BEANS.Sectores;
import com.club.BEANS.Socio;
import com.club.BEANS.TipoDocumentoEnum;
import com.club.BEANS.Venta;
import com.club.DAOs.ArticuloDAO;
import com.club.DAOs.ArticulosVentaDAO;
import com.club.DAOs.CajaDAO;
import com.club.DAOs.ParametrosDAO;
import com.club.DAOs.SectorDAO;
import com.club.DAOs.SocioDAO;
import com.club.DAOs.VentaDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class RegistraVenta extends javax.swing.JInternalFrame {

    List<Socio> listSocios;
    List<Articulo> listArticulos;
    ArticulosVentaTableModel tableModel;
    List<ArticulosVenta> listArticulosVenta;
    Venta venta;
    ArticuloDAO articulosDAO;
    SocioDAO socioDAO;
    CajaDAO cajaDAO;

    ArticulosVentaDAO articulosVentaDAO;
    VentaDAO ventaDAO;
    double subTotal;
    double IVA;
    double redondeo;
    private static String XML_PEDIDOS = "";
    Socio socioSeleccionado;
    Parametros parametros;

    public RegistraVenta() {
        initComponents();

        dpFecha.setDate(new Date());

        defineModelo();

        cbTipo.setModel(new DefaultComboBoxModel(TipoDocumentoEnum.values()));
        AutoCompleteDecorator.decorate(cbSocio);
        AutoCompleteDecorator.decorate(cbArticulos);
        cargaComboArticulos();
        cargaComboSocios();
        comboSectores();
        parametros = (Parametros) new ParametrosDAO().BuscaPorID(Parametros.class, 1);

    }

    void comboSectores() {
        cbSector.removeAllItems();
        SectorDAO sectorDAO = new SectorDAO();
        List<Sectores> sectores = sectorDAO.BuscaTodos(Sectores.class);
        for (Sectores sector : sectores) {
            cbSector.addItem(sector);
        }
    }

    private void cargaComboSocios() {

        try {
            listSocios = new ArrayList();

            socioDAO = new SocioDAO();
            listSocios = socioDAO.BuscaTodos(Socio.class);

            cbSocio.removeAllItems();

            for (Socio Socios : listSocios) {

                cbSocio.addItem(Socios);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar Socios" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void cargaComboArticulos() {
        try {
            articulosDAO = new ArticuloDAO();

            cbArticulos.removeAllItems();
            listArticulos = articulosDAO.BuscaTodosPorSituacion("Activo");

            for (Articulo articulo : listArticulos) {

                cbArticulos.addItem(articulo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar Socios" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void defineModelo() {

        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        txtCantidad.setDocument(new ControlarEntradaTexto(10, chs));

        ((DefaultTableCellRenderer) tblArticulosVenta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listArticulosVenta = new ArrayList<ArticulosVenta>();
        tableModel = new ArticulosVentaTableModel(listArticulosVenta, txtTotal);

        tblArticulosVenta.setModel(tableModel);

        int[] anchos = {5, 100, 200, 20, 20, 30};

        for (int i = 0; i < tblArticulosVenta.getColumnCount(); i++) {

            tblArticulosVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        ListSelectionModel listModel = tblArticulosVenta.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblArticulosVenta.getSelectedRow() != -1) {
                    btnEliminar.setEnabled(true);
                } else {
                    btnEliminar.setEnabled(false);
                }
            }
        });

    }

    private void registraVenta() {

        try {
            if (listArticulosVenta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione um artigo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                venta = new Venta();
                venta.setFecha(dpFecha.getDate());
                venta.setSocio((Socio) cbSocio.getSelectedItem());
                venta.setMoneda(MonedaEnum.REALES);

                venta.setTipoDocumentoEnum((TipoDocumentoEnum) cbTipo.getSelectedItem());

                venta.setArticulosVenta(listArticulosVenta);
                venta.setObservaciones(txtObservaciones.getText());

                Double total = 0.0;

                for (ArticulosVenta articulosVenta : listArticulosVenta) {
                    total = total + articulosVenta.getValor();
                    articulosVenta.setVenta(venta);
                }
                venta.setTotal(total);
                if (venta.getTipoDocumentoEnum() == TipoDocumentoEnum.CREDITO) {
                    venta.setSaldo(total);
                } else {
                    venta.setSaldo(0.00);
                    cajaDAO = new CajaDAO();
                    Caja movCaja = new Caja();
                    movCaja.setConcepto("Venda artigos a vista");
                    movCaja.setEntrada(total);
                    movCaja.setFechaMovimiento(dpFecha.getDate());
                    movCaja.setRubro(parametros.getRubroVentas());
                    movCaja.setSalida(0.0);
                    movCaja.setSaldo(buscaSaldoAnterior() + movCaja.getEntrada());
                    movCaja.setSectores((Sectores) cbSector.getSelectedItem());
                    cajaDAO.Salvar(movCaja);

                }
                ventaDAO = new VentaDAO();
                ventaDAO.Salvar(venta);

                for (ArticulosVenta articulosVenta : listArticulosVenta) {
                    articulosDAO = new ArticuloDAO();

                    articulosVenta.getArticulo().setCantidad(articulosVenta.getArticulo().getCantidad() - articulosVenta.getCantidad());
                    articulosDAO.Actualizar(articulosVenta.getArticulo());
                }
            }

            JOptionPane.showMessageDialog(this, "Venta realizada correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al salvar en base de datos: " + ex);
        }
    }

    void limbiaCampo() {
        txtCantidad.setText("");
        txtTotal.setText("");
        txtObservaciones.setText("");
    }

    private void retirarArticulo() {

        if (tblArticulosVenta.getSelectedRow() != -1) {
            tableModel.eliminar(tblArticulosVenta.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void agregarArticuloPedido(Articulo articulo) {
        if (listArticulos.contains(articulo)) {

            cbArticulos.setSelectedItem(articulo);
        } else {

            cargaComboArticulos();
            cbArticulos.setSelectedItem(articulo);
        }
    }

    public void agregarSocio(Socio Socio) {
        if (listSocios.contains(Socio)) {
            cbSocio.setSelectedItem(Socio);
        } else {
            cbSocio.addItem(Socio);
            cbSocio.setSelectedItem(Socio);
        }
    }

    Double buscaSaldoAnterior() {
        Double saldoAnterior = 0.0;
        cajaDAO = new CajaDAO();
        saldoAnterior = cajaDAO.BuscaSaldoAnterior().getSaldo();
        return saldoAnterior;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnSelecionaCliente1 = new javax.swing.JButton();
        cbSocio = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        dpFecha = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        cbSector = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulosVenta = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbArticulos = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnRegistraVenta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Vendas");
        setPreferredSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar venda");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        btnSelecionaCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/search.png"))); // NOI18N
        btnSelecionaCliente1.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(btnSelecionaCliente1, gridBagConstraints);

        cbSocio.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSocioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbSocio, gridBagConstraints);

        jLabel11.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Tipo venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel12, gridBagConstraints);

        cbTipo.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbTipo, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel3.setText("Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel11.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel11.add(dpFecha, gridBagConstraints);

        jLabel4.setText("Setor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel4, gridBagConstraints);

        cbSector.setEnabled(false);
        cbSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSectorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbSector, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        tblArticulosVenta.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblArticulosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblArticulosVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblArticulosVenta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jScrollPane1, gridBagConstraints);

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/search.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar articulo");
        btnBuscar.setBorder(null);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnBuscar, gridBagConstraints);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/add.png"))); // NOI18N
        btnNuevo.setToolTipText("Nueva posición");
        btnNuevo.setBorder(null);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnNuevo, gridBagConstraints);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/delete.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar posición");
        btnEliminar.setBorder(null);
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnEliminar, gridBagConstraints);

        cbArticulos.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbArticulosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(cbArticulos, gridBagConstraints);

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(txtCantidad, gridBagConstraints);

        jLabel8.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel8, gridBagConstraints);

        jLabel13.setText("Articulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel3.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Total");
        jPanel8.add(jLabel10, new java.awt.GridBagConstraints());

        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("$ #,##0.00"))));
        txtTotal.setEnabled(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(txtTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel8, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnRegistraVenta.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnRegistraVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imagenes/pedido.png"))); // NOI18N
        btnRegistraVenta.setMnemonic('R');
        btnRegistraVenta.setText("Registrar");
        btnRegistraVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraVentaActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistraVenta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Observaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraVentaActionPerformed

        registraVenta();
        listArticulosVenta.clear();
        tableModel.fireTableDataChanged();


    }//GEN-LAST:event_btnRegistraVentaActionPerformed

    private void btnSelecionaSocio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaSocio1ActionPerformed


    }//GEN-LAST:event_btnSelecionaSocio1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ArticulosFrame articulosFrame = new ArticulosFrame(this);
        this.getDesktopPane().add(articulosFrame);
        articulosFrame.setVisible(true);
        articulosFrame.toFront();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        Articulo articulo = (Articulo) cbArticulos.getSelectedItem();
        Double cantidad = Double.valueOf(txtCantidad.getText());

        tableModel.agregar(new ArticulosVenta(listArticulosVenta.size() + 1, articulo, cantidad));

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        retirarArticulo();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed

        Articulo articulo = (Articulo) cbArticulos.getSelectedItem();
        Double cantidad = Double.valueOf(txtCantidad.getText());

        tableModel.agregar(new ArticulosVenta(listArticulosVenta.size() + 1, articulo, cantidad));


    }//GEN-LAST:event_txtCantidadActionPerformed

    private void cbSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSocioActionPerformed

        socioSeleccionado = (Socio) cbSocio.getSelectedItem();

    }//GEN-LAST:event_cbSocioActionPerformed

    private void cbArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbArticulosActionPerformed

        //cbEquivalencias.removeAllItems();
        /*for (EquivalenciaUnidades equivalenciaUnidades : ((Articulo) cbArticulos.getSelectedItem()).getListEquivalencias()) {
            cbEquivalencias.addItem(equivalenciaUnidades);
        }*/
    }//GEN-LAST:event_cbArticulosActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoActionPerformed

    private void cbSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSectorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistraVenta;
    private javax.swing.JButton btnSelecionaCliente1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbArticulos;
    private javax.swing.JComboBox cbSector;
    private javax.swing.JComboBox cbSocio;
    private javax.swing.JComboBox cbTipo;
    private org.jdesktop.swingx.JXDatePicker dpFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblArticulosVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
