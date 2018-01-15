package com.club.views;

import com.club.BEANS.Caja;
import com.club.BEANS.CcCobrador;
import com.club.BEANS.Mensualidades;
import com.club.BEANS.MensualidadesAnuladas;
import com.club.BEANS.MonedaEnum;
import com.club.BEANS.PagosVentas;
import com.club.BEANS.Parametros;
import com.club.BEANS.Rubro;
import com.club.BEANS.Sectores;
import com.club.BEANS.Socio;
import com.club.BEANS.Venta;
import com.club.DAOs.CajaDAO;
import com.club.DAOs.CcCobradorDAO;
import com.club.DAOs.MensualidadesAnuladasDAO;
import com.club.DAOs.MensualidadesDAO;
import com.club.DAOs.PagosVentasDAO;
import com.club.DAOs.ParametrosDAO;
import com.club.DAOs.SocioDAO;
import com.club.DAOs.VentaDAO;
import com.club.Renderers.MeDateCellRenderer;
import com.club.Renderers.TableRendererColor;
import com.club.Renderers.TableRendererColorSaldo;
import com.club.control.utilidades.LeeProperties;
import com.club.tableModels.MensualidadesTableModel;
import com.club.tableModels.PagosVentasTableModel;
import com.club.tableModels.SocioTableModel;
import com.club.tableModels.VentasTableModel;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ConsultaMensualidades extends javax.swing.JInternalFrame {

    MensualidadesDAO mensualidadesDAO;
    ParametrosDAO parametrosDAO;
    SocioDAO socioDAO;
    VentaDAO ventaDAO;
    PagosVentasDAO pagosVentasDAO;
    MensualidadesAnuladasDAO mensualidadesAnuladasDAO;
    CcCobradorDAO ccCobradorDAO;
    Socio socioSeleccionado;
    Venta ventaSeleccionada;
    CcCobrador credito;
    SocioTableModel tblModelSocio;
    MensualidadesTableModel tblModelMensualidades;
    VentasTableModel tblModelVentas;
    PagosVentasTableModel tblModelPagosventas;
    ListSelectionModel listModelSocios;
    ListSelectionModel listModelMensualidades;
    Mensualidades mensualidadSeleccionada;
    List<Socio> listSocios;
    List<Mensualidades> listMensualidades;
    List<Venta> listVentas;
    List<PagosVentas> listPagos;
    LeeProperties props = new LeeProperties();
    Parametros param;

    public ConsultaMensualidades() {

        initComponents();
        defineModelo();
        parametrosDAO = new ParametrosDAO();
        param = (Parametros) parametrosDAO.BuscaPorID(Parametros.class, 1);
        Calendar unMesAdelante = Calendar.getInstance();
        unMesAdelante.add(Calendar.MONTH, 1);
        dpVencimiento.setDate(unMesAdelante.getTime());
        muestraContenidoTabla();
        

    }

    public ConsultaMensualidades(Socio socio) {

        initComponents();
        txtFiltro.setText(socio.getId().toString());
        defineModelo();
        muestraContenidoTabla();
        tblSocio.setRowSelectionInterval(0, 0);
        parametrosDAO = new ParametrosDAO();
        param = (Parametros) parametrosDAO.BuscaPorID(Parametros.class, 1);
        Calendar unMesAdelante = Calendar.getInstance();
        unMesAdelante.add(Calendar.MONTH, 1);
        dpVencimiento.setDate(unMesAdelante.getTime());
    }

    private void defineModelo() {

        ((DefaultTableCellRenderer) tblSocio.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tblMensualidades.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tblPagos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tblVentas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listSocios = new ArrayList<Socio>();
        tblModelSocio = new SocioTableModel(listSocios);
        tblSocio.setModel(tblModelSocio);
        tblSocio.setRowHeight(20);
        listMensualidades = new ArrayList<Mensualidades>();
        tblModelMensualidades = new MensualidadesTableModel(listMensualidades);
        tblMensualidades.setModel(tblModelMensualidades);
        tblMensualidades.setRowHeight(20);
        //tblMensualidades.getColumn("Pago").setCellRenderer(new TableRendererColorSituacion(5));
        tblMensualidades.removeColumn(tblMensualidades.getColumn("Talón CobrosYa"));
        tblMensualidades.removeColumn(tblMensualidades.getColumn("Socio"));
        tblMensualidades.removeColumn(tblMensualidades.getColumn("Enviado"));
        tblMensualidades.removeColumn(tblMensualidades.getColumn("Situación"));
        tblMensualidades.getColumn("Vencimiento").setCellRenderer(new MeDateCellRenderer());
        //tblMensualidades.getColumn("Pago").setCellRenderer(new MeDateCellRenderer());
        tblMensualidades.getColumn("Pago").setCellRenderer(new TableRendererColor(5));

        tblVentas.setRowHeight(20);
        listVentas = new ArrayList<Venta>();
        tblModelVentas = new VentasTableModel(listVentas);
        tblVentas.setModel(tblModelVentas);
        tblVentas.getColumn("Data").setCellRenderer(new MeDateCellRenderer());
        tblVentas.getColumn("Saldo").setCellRenderer(new TableRendererColorSaldo(4));

        listPagos = new ArrayList<PagosVentas>();
        tblModelPagosventas = new PagosVentasTableModel(listPagos);
        tblPagos.setModel(tblModelPagosventas);
        tblPagos.setRowHeight(20);
        tblPagos.getColumn("Data pagamento").setCellRenderer(new MeDateCellRenderer());
        /*int[] anchos = {5, 100, 200, 20, 20, 30};
        for (int i = 0; i < tblArticulosVenta.getColumnCount(); i++) {
            tblArticulosVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }*/
        ListSelectionModel listModel = tblSocio.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblSocio.getSelectedRow() != -1) {
                    socioSeleccionado = listSocios.get(tblSocio.getSelectedRow());
                    muestraMensualidades();
                    muestraComprasPorSocio();
                    btnGenerarRecibo.setEnabled(true);
                } else {
                    btnGenerarRecibo.setEnabled(false);
                }

            }
        });

        ListSelectionModel listModelVentas = tblVentas.getSelectionModel();
        listModelVentas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblVentas.getSelectedRow() != -1) {
                    ventaSeleccionada = listVentas.get(tblVentas.getSelectedRow());
                    if (ventaSeleccionada.getSaldo() != 0.0) {
                        btnConfirmaPagoVenta.setEnabled(true);
                    } else {
                        btnConfirmaPagoVenta.setEnabled(false);
                    }
                    muestraPagosPorCompras();
                }
            }
        });

        ListSelectionModel listModelMensualidad = tblMensualidades.getSelectionModel();
        listModelMensualidad.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblMensualidades.getSelectedRow() != -1) {
                    mensualidadSeleccionada = listMensualidades.get(tblMensualidades.getSelectedRow());

                    btnReimprimeRecibo.setEnabled(true);

                    btnAnularRecibo.setEnabled(true);
                } else {
                    btnReimprimeRecibo.setEnabled(false);
                    btnAnularRecibo.setEnabled(false);
                }
            }
        });

    }

    private void muestraContenidoTabla() {

        socioDAO = new SocioDAO();
        listSocios.clear();
        listSocios.addAll(socioDAO.FiltroInteligenteSocios(txtFiltro.getText()));
        tblModelSocio.fireTableDataChanged();
    }

    private void muestraMensualidades() {

        mensualidadesDAO = new MensualidadesDAO();
        listMensualidades.clear();
        listMensualidades.addAll(mensualidadesDAO.BuscaMensualidadesPorSocio(socioSeleccionado));
        tblModelMensualidades.fireTableDataChanged();

    }

    private void muestraPagosPorCompras() {

        pagosVentasDAO = new PagosVentasDAO();
        listPagos.clear();
        listPagos.addAll(pagosVentasDAO.buscaPagosPorVenta(ventaSeleccionada));
        tblModelPagosventas.fireTableDataChanged();

    }

    private void muestraComprasPorSocio() {
        ventaDAO = new VentaDAO();
        listVentas.clear();
        listVentas.addAll(ventaDAO.buscaVentasPorSocioCredito(socioSeleccionado));
        tblModelVentas.fireTableDataChanged();
    }

    private void AnulaRecibo() {

        int confirmación = JOptionPane.showConfirmDialog(null, "Confirma la anulación de la Mensualidad Seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmación == JOptionPane.YES_OPTION) {
            try {
                String motivo = JOptionPane.showInputDialog(null, "Motivo?");

                mensualidadesAnuladasDAO = new MensualidadesAnuladasDAO();
                MensualidadesAnuladas mensualidadesAnlada = new MensualidadesAnuladas();
                mensualidadesAnlada.setFechaPago(mensualidadSeleccionada.getFechaPago());
                mensualidadesAnlada.setFechaVencimiento(mensualidadSeleccionada.getFechaVencimiento());
                mensualidadesAnlada.setPago(mensualidadSeleccionada.getPago());
                mensualidadesAnlada.setSocio(mensualidadSeleccionada.getSocio());
                mensualidadesAnlada.setValor(mensualidadSeleccionada.getValor());
                mensualidadesAnlada.setFechaAnulacion(new Date());
                mensualidadesAnlada.setMotivo(motivo);
                mensualidadesAnuladasDAO.Salvar(mensualidadesAnlada);

                mensualidadesDAO = new MensualidadesDAO();
                mensualidadesDAO.EliminarPorId(Mensualidades.class, mensualidadSeleccionada.getId());

                Caja pago = new Caja();
                pago.setConcepto("Anula mensualidad socio '" + mensualidadSeleccionada.getSocio());
                pago.setRubro(new Rubro(1));
                pago.setFechaMovimiento(new Date());
                pago.setEntrada(0.0);
                pago.setSalida(mensualidadSeleccionada.getValor());
                pago.setSaldo(buscaSaldoAnterior() - pago.getSalida());
                pago.setSectores(new Sectores(1));

                CajaDAO cajaDAO = new CajaDAO();
                cajaDAO.Salvar(pago);

                JOptionPane.showMessageDialog(null, "Mensualidad anulada correctamente");
                muestraMensualidades();
            } catch (Exception ex) {
                Logger.getLogger(ConsultaMensualidades.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void EmisionMensualidad() throws Exception {

        socioDAO = new SocioDAO();

        mensualidadesDAO = new MensualidadesDAO();
        if (mensualidadesDAO.VerificaSiYaFueEmitida(socioSeleccionado, dpVencimiento.getDate()) == true) {

            mensualidadesDAO = new MensualidadesDAO();

            //Cobrador cobrador = socio.getCobrador();
            Double valorMensualidad = socioSeleccionado.getCategoria().getMensualidad();

            Mensualidades mensualidad = new Mensualidades();
            mensualidad.setPago(false);
            mensualidad.setSocio(socioSeleccionado);
            mensualidad.setValor(valorMensualidad);
            mensualidad.setFechaVencimiento(dpVencimiento.getDate());

            mensualidadesDAO = new MensualidadesDAO();
            mensualidadesDAO.Salvar(mensualidad);
            registraPago(mensualidad);
            imprimeRecibo(mensualidad.getId());
        }
    }

    void imprimeRecibo(Integer id) {
        try {

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("Reportes/recibos_1.jasper");
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("recibo", id);
            parametros.put("logo", "Imagenes/SmallLogoBW.jpg");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al imprimir recibo " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    private void registraPago(Mensualidades mensualidad) {

        try {

            mensualidadesDAO = new MensualidadesDAO();

            int confirmacion = JOptionPane.showConfirmDialog(null, "Comfirma o pagamento da mensalidade:"
                    + " " + mensualidadSeleccionada + " correspondente a : " + mensualidad.getSocio() + "");

            if (confirmacion == JOptionPane.YES_OPTION) {

                mensualidad.setFechaPago(new Date());
                mensualidad.setPago(true);

                mensualidadesDAO = new MensualidadesDAO();
                mensualidadesDAO.Actualizar(mensualidad);

                Caja pago = new Caja();
                pago.setConcepto("Cobro mensualidad socio '" + mensualidad.getSocio());
                pago.setRubro(new Rubro(1));
                pago.setFechaMovimiento(new Date());
                pago.setEntrada(mensualidad.getValor());
                pago.setSalida(0.0);
                pago.setSaldo(buscaSaldoAnterior() + pago.getEntrada());
                pago.setSectores(new Sectores(1));

                CajaDAO cajaDAO = new CajaDAO();
                cajaDAO.Salvar(pago);

                JOptionPane.showMessageDialog(null, "Pago registrado");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar el SQL deseado : " + ex + "");
            ex.printStackTrace();
        }
        muestraContenidoTabla();
        muestraMensualidades();
    }

    Double buscaSaldoAnterior() {
        Double saldoAnterior = 0.0;
        CajaDAO cajaDAO = new CajaDAO();
        saldoAnterior = cajaDAO.BuscaSaldoAnterior().getSaldo();
        return saldoAnterior;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSocio = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMensualidades = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnGenerarRecibo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dpVencimiento = new org.jdesktop.swingx.JXDatePicker();
        btnAnularRecibo = new javax.swing.JButton();
        btnReimprimeRecibo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnConfirmaPagoVenta = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Criterio de busca:"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(txtFiltro, gridBagConstraints);

        btnBuscar.setText("Buscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnBuscar, gridBagConstraints);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consulta de Mesalidades e contas"); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensalidades"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        tblMensualidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane2, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        btnGenerarRecibo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGenerarRecibo.setText("Gerar recíbo");
        btnGenerarRecibo.setEnabled(false);
        btnGenerarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReciboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnGenerarRecibo, gridBagConstraints);

        jLabel4.setText("Data vencimento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(dpVencimiento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        jPanel6.add(jPanel8, gridBagConstraints);

        btnAnularRecibo.setText("Anular Recibo");
        btnAnularRecibo.setEnabled(false);
        btnAnularRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularReciboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnAnularRecibo, gridBagConstraints);

        btnReimprimeRecibo.setText("Re-imprimir recibo");
        btnReimprimeRecibo.setEnabled(false);
        btnReimprimeRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimeReciboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnReimprimeRecibo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanel6, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendas"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPagos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jScrollPane4, gridBagConstraints);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblVentas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jScrollPane5, gridBagConstraints);

        btnConfirmaPagoVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirmaPagoVenta.setText("Registrar pagamento");
        btnConfirmaPagoVenta.setEnabled(false);
        btnConfirmaPagoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaPagoVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnConfirmaPagoVenta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        muestraContenidoTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblMensualidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMensualidadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMensualidadesMouseClicked

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed

        muestraContenidoTabla();

    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnAnularReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularReciboActionPerformed

        AnulaRecibo();

    }//GEN-LAST:event_btnAnularReciboActionPerformed

    private void tblPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagosMouseClicked

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblVentasMouseClicked

    private void btnGenerarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReciboActionPerformed

        try {
            EmisionMensualidad();
            muestraMensualidades();

        } catch (Exception ex) {
            Logger.getLogger(ConsultaMensualidades.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGenerarReciboActionPerformed

    private void btnConfirmaPagoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaPagoVentaActionPerformed
        PagoVentaDialog pagoVentaDialog = new PagoVentaDialog(null, true, ventaSeleccionada.getSaldo());
        pagoVentaDialog.setVisible(true);
        pagoVentaDialog.toFront();

        Double entrega = pagoVentaDialog.getValor();

        ventaSeleccionada.setSaldo(ventaSeleccionada.getSaldo() - entrega);

        ventaDAO = new VentaDAO();
        ventaDAO.Actualizar(ventaSeleccionada);

        PagosVentas pagoVenta = new PagosVentas();
        pagoVenta.setFecha(new Date());
        pagoVenta.setMoneda(MonedaEnum.REALES);
        pagoVenta.setValor(entrega);
        pagoVenta.setVenta(ventaSeleccionada);
        pagosVentasDAO = new PagosVentasDAO();
        pagosVentasDAO.Salvar(pagoVenta);

        CajaDAO cajaDAO = new CajaDAO();
        Caja movCaja = new Caja();
        movCaja.setConcepto("Venda " + ventaSeleccionada.getId() + ", socio" + ventaSeleccionada.getSocio());
        movCaja.setEntrada(entrega);
        movCaja.setFechaMovimiento(new Date());
        movCaja.setRubro(param.getRubroVentas());
        movCaja.setSalida(0.0);
        movCaja.setSaldo(buscaSaldoAnterior() + movCaja.getEntrada());
        movCaja.setSectores(new Sectores(1));
        cajaDAO.Salvar(movCaja);

        JOptionPane.showMessageDialog(null, "Pago registrado");
        muestraComprasPorSocio();
        muestraPagosPorCompras();


    }//GEN-LAST:event_btnConfirmaPagoVentaActionPerformed

    private void btnReimprimeReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimeReciboActionPerformed

        imprimeRecibo(mensualidadSeleccionada.getId());

    }//GEN-LAST:event_btnReimprimeReciboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularRecibo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConfirmaPagoVenta;
    private javax.swing.JButton btnGenerarRecibo;
    private javax.swing.JButton btnReimprimeRecibo;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXDatePicker dpVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblMensualidades;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTable tblSocio;
    private javax.swing.JTable tblVentas;
    public javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
