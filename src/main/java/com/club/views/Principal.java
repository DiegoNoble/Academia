package com.club.views;

import com.academia.Articulos.ArticulosFrame;
import com.academia.Articulos.CategoriasDialog;
import com.academia.Articulos.RegistraVenta;
import com.club.BEANS.Caja;
import com.club.BEANS.Usuario;
import com.club.DAOs.CajaDAO;
import com.club.control.utilidades.DecorateDesktopPane;
import com.club.control.utilidades.data;
import com.club.smsmasivos.SMSMasivosController;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import com.club.smsmasivos.RecibeRespuestasSMSController;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    data muestraData;
    data muestraHora;
    Usuario usuario;
    DecorateDesktopPane jDesktopPane = new DecorateDesktopPane("/Imagenes/400dpiLogoCroppedMedio.jpg");
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/SmallLogo.png"));

    public Principal(Usuario usuario) {

        initComponents();
        this.usuario = usuario;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(jDesktopPane);

        muestraData = new data();
        muestraData.le_data();

        if (usuario.getPerfil().equals("Usuario")) {

            mnuRegistros.setEnabled(false);
            mnuMensualidades.setEnabled(false);
            mnuItemUsuarios.setEnabled(false);
            //   mniItemMovimientosCaja.setEnabled(false);
        }

        if (usuario.getPerfil().equals("Operador")) {

            mnuItemUsuarios.setEnabled(false);

        }

        if (usuario.getPerfil().equals("Gerencia")) {

            mnuItemUsuarios.setEnabled(false);
        }

        if (usuario.getPerfil().equals("Portero")) {

            mnuItemUsuarios.setVisible(false);
            mnuRegistros.setVisible(false);
            mnuMensualidades.setVisible(false);

            mnuConsultas.setVisible(false);
            mnuControlPresencia.setVisible(false);
            mnuCampanaSMS.setVisible(false);

        }

        AlertaSociosAtrasados alertaSociosAtrasados = new AlertaSociosAtrasados();
        jDesktopPane.add(alertaSociosAtrasados);
        alertaSociosAtrasados.setVisible(true);
        alertaSociosAtrasados.setLocation(0, 0);

    }

    private void centralizaVentanas(JInternalFrame internalFrame) {
        internalFrame.setLocation((this.getWidth() - internalFrame.getWidth()) / 2,
                (this.getHeight() - internalFrame.getHeight()) / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        mnuItemUsuarios = new javax.swing.JMenuItem();
        mnuItemUsuarios1 = new javax.swing.JMenuItem();
        mnuItemUsuarios2 = new javax.swing.JMenuItem();
        mnuItemUsuarios3 = new javax.swing.JMenuItem();
        mnuItemSalir = new javax.swing.JMenuItem();
        mnuRegistros = new javax.swing.JMenu();
        mnuItemSocios = new javax.swing.JMenuItem();
        mnuItemCategoria = new javax.swing.JMenuItem();
        mnuItemCategoria1 = new javax.swing.JMenuItem();
        mnuItemCategoria2 = new javax.swing.JMenuItem();
        mnuItemCaja1 = new javax.swing.JMenuItem();
        mnuItemCaja3 = new javax.swing.JMenuItem();
        mnuItemCaja4 = new javax.swing.JMenuItem();
        mnuItemCaja5 = new javax.swing.JMenuItem();
        mnuConsultas = new javax.swing.JMenu();
        mnuItemConsultaSocio1 = new javax.swing.JMenuItem();
        mnuItemConsultaSocio3 = new javax.swing.JMenuItem();
        mniItemMovimientosCaja1 = new javax.swing.JMenuItem();
        mniItemMovimientosCaja2 = new javax.swing.JMenuItem();
        mnuMensualidades = new javax.swing.JMenu();
        mnuitemConsultaMensualidades = new javax.swing.JMenuItem();
        mnuItemRecibos1 = new javax.swing.JMenuItem();
        mnuControlPresencia = new javax.swing.JMenu();
        mnuItemArqueo1 = new javax.swing.JMenuItem();
        mnuItemMarcajes = new javax.swing.JMenuItem();
        mnuItemArqueo2 = new javax.swing.JMenuItem();
        mnuCampanaSMS = new javax.swing.JMenu();
        mnuItemArqueo3 = new javax.swing.JMenuItem();
        mnuItemArqueo4 = new javax.swing.JMenuItem();
        mnuItemArqueo5 = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();
        mnuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sis. Control de Clubes -- DNSoft .");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/400dpiLogo.png")).getImage());

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        mnuSistema.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuSistema.setText("Sistema"); // NOI18N
        mnuSistema.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemUsuarios.setText("Usuarios");
        mnuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemUsuariosActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemUsuarios);

        mnuItemUsuarios1.setText("Parâmetros");
        mnuItemUsuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemUsuarios1ActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemUsuarios1);

        mnuItemUsuarios2.setText("Atualizar saldo de caixa");
        mnuItemUsuarios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemUsuarios2ActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemUsuarios2);

        mnuItemUsuarios3.setText("Backup Banco de Dados");
        mnuItemUsuarios3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemUsuarios3ActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemUsuarios3);

        mnuItemSalir.setText("Salir"); // NOI18N
        mnuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSalirActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemSalir);

        jMenuBar1.add(mnuSistema);

        mnuRegistros.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuRegistros.setText("Cadastros "); // NOI18N
        mnuRegistros.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemSocios.setText("Socios"); // NOI18N
        mnuItemSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSociosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemSocios);

        mnuItemCategoria.setText("Categorias"); // NOI18N
        mnuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCategoriaActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCategoria);

        mnuItemCategoria1.setText("Contas"); // NOI18N
        mnuItemCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCategoria1ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCategoria1);

        mnuItemCategoria2.setText("Setores"); // NOI18N
        mnuItemCategoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCategoria2ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCategoria2);

        mnuItemCaja1.setText("Fluxo de Caixa");
        mnuItemCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCaja1ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCaja1);

        mnuItemCaja3.setText("Artigos");
        mnuItemCaja3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCaja3ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCaja3);

        mnuItemCaja4.setText("Categorias de artigos");
        mnuItemCaja4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCaja4ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCaja4);

        mnuItemCaja5.setText("Vendas ");
        mnuItemCaja5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCaja5ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemCaja5);

        jMenuBar1.add(mnuRegistros);

        mnuConsultas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuConsultas.setText("Consultas");
        mnuConsultas.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemConsultaSocio1.setText("Teste de digital");
        mnuItemConsultaSocio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemConsultaSocio1ActionPerformed(evt);
            }
        });
        mnuConsultas.add(mnuItemConsultaSocio1);

        mnuItemConsultaSocio3.setText("Socios por digital");
        mnuItemConsultaSocio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemConsultaSocio3ActionPerformed(evt);
            }
        });
        mnuConsultas.add(mnuItemConsultaSocio3);

        mniItemMovimientosCaja1.setText("Fluxo de caixa");
        mniItemMovimientosCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniItemMovimientosCaja1ActionPerformed(evt);
            }
        });
        mnuConsultas.add(mniItemMovimientosCaja1);

        mniItemMovimientosCaja2.setText("Socios atrasados");
        mniItemMovimientosCaja2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniItemMovimientosCaja2ActionPerformed(evt);
            }
        });
        mnuConsultas.add(mniItemMovimientosCaja2);

        jMenuBar1.add(mnuConsultas);

        mnuMensualidades.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuMensualidades.setText("Mensalidades e contas");
        mnuMensualidades.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuitemConsultaMensualidades.setText("Emissão/Pagamentos ");
        mnuitemConsultaMensualidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuitemConsultaMensualidadesActionPerformed(evt);
            }
        });
        mnuMensualidades.add(mnuitemConsultaMensualidades);

        mnuItemRecibos1.setText("Consultas  mensalidades");
        mnuItemRecibos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemRecibos1ActionPerformed(evt);
            }
        });
        mnuMensualidades.add(mnuItemRecibos1);

        jMenuBar1.add(mnuMensualidades);

        mnuControlPresencia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuControlPresencia.setText("Controle de presença");
        mnuControlPresencia.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemArqueo1.setText(" Funcionarios");
        mnuItemArqueo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemArqueo1ActionPerformed(evt);
            }
        });
        mnuControlPresencia.add(mnuItemArqueo1);

        mnuItemMarcajes.setText("Marcajens por digital");
        mnuItemMarcajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemMarcajesActionPerformed(evt);
            }
        });
        mnuControlPresencia.add(mnuItemMarcajes);

        mnuItemArqueo2.setText("Controle de marcações ");
        mnuItemArqueo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemArqueo2ActionPerformed(evt);
            }
        });
        mnuControlPresencia.add(mnuItemArqueo2);

        jMenuBar1.add(mnuControlPresencia);

        mnuCampanaSMS.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuCampanaSMS.setText("Campanhas comunicação por SMS");
        mnuCampanaSMS.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemArqueo3.setText("Nova campanha");
        mnuItemArqueo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemArqueo3ActionPerformed(evt);
            }
        });
        mnuCampanaSMS.add(mnuItemArqueo3);

        mnuItemArqueo4.setText("Procesar respostas SMS");
        mnuItemArqueo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemArqueo4ActionPerformed(evt);
            }
        });
        mnuCampanaSMS.add(mnuItemArqueo4);

        mnuItemArqueo5.setText("Consultar campanhas");
        mnuItemArqueo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemArqueo5ActionPerformed(evt);
            }
        });
        mnuCampanaSMS.add(mnuItemArqueo5);

        jMenuBar1.add(mnuCampanaSMS);

        mnuAyuda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuAyuda.setText("Ayuda"); // NOI18N
        mnuAyuda.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        mnuItemSobre.setText("Sobre"); // NOI18N
        mnuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSobreActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuItemSobre);

        jMenuBar1.add(mnuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuItemSalirActionPerformed

    private void mnuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSobreActionPerformed


    }//GEN-LAST:event_mnuItemSobreActionPerformed

    private void mnuItemSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSociosActionPerformed

        SocioFrameCompleto socio = new SocioFrameCompleto();
        jDesktopPane.add(socio);
        socio.setVisible(true);
        try {
            socio.setMaximum(true);
            //centralizaVentanas(socio);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_mnuItemSociosActionPerformed

    private void mnuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoriaActionPerformed

        CategoriaFrame categoria = new CategoriaFrame();
        jDesktopPane.add(categoria);
        categoria.setVisible(true);
        centralizaVentanas(categoria);

    }//GEN-LAST:event_mnuItemCategoriaActionPerformed

    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime


    }//GEN-LAST:event_timer1OnTime

    private void mnuitemConsultaMensualidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuitemConsultaMensualidadesActionPerformed

        ConsultaMensualidades mensualidades = new ConsultaMensualidades();
        jDesktopPane.add(mensualidades);
        mensualidades.setVisible(true);
        centralizaVentanas(mensualidades);
        try {
            mensualidades.setMaximum(true);
            //centralizaVentanas(socio);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_mnuitemConsultaMensualidadesActionPerformed

    private void mnuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemUsuariosActionPerformed

        registroUsuarios usuarios = new registroUsuarios();
        jDesktopPane.add(usuarios);
        usuarios.setVisible(true);
        centralizaVentanas(usuarios);
    }//GEN-LAST:event_mnuItemUsuariosActionPerformed

    private void mnuItemConsultaSocio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemConsultaSocio1ActionPerformed

        ConsultaSocioxHuella huella = new ConsultaSocioxHuella();
        jDesktopPane.add(huella);
        huella.setVisible(true);

    }//GEN-LAST:event_mnuItemConsultaSocio1ActionPerformed

    private void mnuItemConsultaSocio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemConsultaSocio3ActionPerformed
        ControlDeAccesos mensualidades = new ControlDeAccesos();
        jDesktopPane.add(mensualidades);
        mensualidades.setVisible(true);
        centralizaVentanas(mensualidades);
        try {
            mensualidades.setMaximum(true);
            //centralizaVentanas(socio);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuItemConsultaSocio3ActionPerformed

    private void mnuItemArqueo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArqueo1ActionPerformed

        FuncionarioView funcionarios = new FuncionarioView();
        jDesktopPane.add(funcionarios);
        funcionarios.setVisible(true);
        try {
            funcionarios.setMaximum(true);
            //centralizaVentanas(socio);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuItemArqueo1ActionPerformed

    private void mnuItemMarcajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemMarcajesActionPerformed
        MarcarjeFuncionarioxHuella huella = new MarcarjeFuncionarioxHuella();
        jDesktopPane.add(huella);
        huella.setVisible(true);
    }//GEN-LAST:event_mnuItemMarcajesActionPerformed

    private void mnuItemArqueo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArqueo2ActionPerformed
        ConsultaMarcasFuncionariosView consultaMarcajes = new ConsultaMarcasFuncionariosView();
        jDesktopPane.add(consultaMarcajes);
        consultaMarcajes.setVisible(true);
        try {
            consultaMarcajes.setMaximum(true);
            //centralizaVentanas(socio);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuItemArqueo2ActionPerformed

    private void mnuItemCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoria1ActionPerformed
        RubrosView rubros = new RubrosView();
        jDesktopPane.add(rubros);
        rubros.setVisible(true);
        centralizaVentanas(rubros);
    }//GEN-LAST:event_mnuItemCategoria1ActionPerformed

    private void mnuItemCategoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoria2ActionPerformed
        sectoresFrame sectores = new sectoresFrame();
        jDesktopPane.add(sectores);
        sectores.setVisible(true);
        centralizaVentanas(sectores);

    }//GEN-LAST:event_mnuItemCategoria2ActionPerformed

    private void mnuItemRecibos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemRecibos1ActionPerformed

        ArqueoCobranzasView arqueoCobradores = new ArqueoCobranzasView();
        jDesktopPane.add(arqueoCobradores);
        arqueoCobradores.setVisible(true);
        centralizaVentanas(arqueoCobradores);
    }//GEN-LAST:event_mnuItemRecibos1ActionPerformed

    private void mnuItemArqueo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArqueo3ActionPerformed
        SMSMasivosView smsView = new SMSMasivosView();
        SMSMasivosController controller = new SMSMasivosController(smsView, jDesktopPane);
        controller.go();
    }//GEN-LAST:event_mnuItemArqueo3ActionPerformed

    private void mnuItemArqueo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArqueo4ActionPerformed
        RecibeRespuestasSMSView consulta = new RecibeRespuestasSMSView();
        RecibeRespuestasSMSController controller = new RecibeRespuestasSMSController(consulta, jDesktopPane);
        controller.go();
    }//GEN-LAST:event_mnuItemArqueo4ActionPerformed

    private void mnuItemUsuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemUsuarios1ActionPerformed

        ParametrosView parametrosView = new ParametrosView();
        jDesktopPane.add(parametrosView);
        parametrosView.setVisible(true);
        parametrosView.toFront();
    }//GEN-LAST:event_mnuItemUsuarios1ActionPerformed

    private void mnuItemArqueo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArqueo5ActionPerformed
        ConsultaRespuestasCamapanSMSView camapanSMSView = new ConsultaRespuestasCamapanSMSView();
        jDesktopPane.add(camapanSMSView);
        camapanSMSView.setVisible(true);
        camapanSMSView.toFront();
    }//GEN-LAST:event_mnuItemArqueo5ActionPerformed

    private void mnuItemCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCaja1ActionPerformed

        cajaView caja = new cajaView(usuario.getNombre());
        jDesktopPane.add(caja);
        caja.setVisible(true);
        centralizaVentanas(caja);

    }//GEN-LAST:event_mnuItemCaja1ActionPerformed

    private void mnuItemUsuarios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemUsuarios2ActionPerformed
        try {
            CajaDAO cajaDAO = new CajaDAO();
            List<Caja> BuscaTodosOrdenaPorID = cajaDAO.BuscaTodosOrdenaPorID();
            Double saldo = 0.0;
            for (Caja mov : BuscaTodosOrdenaPorID) {
                saldo = saldo + (mov.getEntrada() - mov.getSalida());
                mov.setSaldo(saldo);
                cajaDAO = new CajaDAO();

                cajaDAO.Actualizar(mov);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }
    }//GEN-LAST:event_mnuItemUsuarios2ActionPerformed

    private void mniItemMovimientosCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniItemMovimientosCaja1ActionPerformed
        consultaCajaViewNEW consultaCaja = new consultaCajaViewNEW();
        jDesktopPane.add(consultaCaja);
        consultaCaja.setVisible(true);
        centralizaVentanas(consultaCaja);
    }//GEN-LAST:event_mniItemMovimientosCaja1ActionPerformed

    private void mnuItemUsuarios3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemUsuarios3ActionPerformed
        BackUpsView logBackup = new BackUpsView();
        logBackup.setVisible(true);
        logBackup.toFront();
    }//GEN-LAST:event_mnuItemUsuarios3ActionPerformed

    private void mnuItemCaja3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCaja3ActionPerformed

        ArticulosFrame articulosFrame = new ArticulosFrame(usuario.getPerfil());
        jDesktopPane.add(articulosFrame);
        articulosFrame.setVisible(true);
        //centralizaVentanas(articulosFrame);
    }//GEN-LAST:event_mnuItemCaja3ActionPerformed

    private void mnuItemCaja4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCaja4ActionPerformed
        CategoriasDialog categoriasDialog = new CategoriasDialog(null, true, null);
        categoriasDialog.setVisible(true);
        categoriasDialog.toFront();
    }//GEN-LAST:event_mnuItemCaja4ActionPerformed

    private void mnuItemCaja5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCaja5ActionPerformed
        RegistraVenta registraVenta = new RegistraVenta();
        jDesktopPane.add(registraVenta);
        registraVenta.setVisible(true);
        //centralizaVentanas(registraVenta);
    }//GEN-LAST:event_mnuItemCaja5ActionPerformed

    private void mniItemMovimientosCaja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniItemMovimientosCaja2ActionPerformed
        AlertaSociosAtrasados alertaSociosAtrasados = new AlertaSociosAtrasados();
        jDesktopPane.add(alertaSociosAtrasados);
        alertaSociosAtrasados.setVisible(true);
        alertaSociosAtrasados.setLocation(0, 0);
    }//GEN-LAST:event_mniItemMovimientosCaja2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mniItemMovimientosCaja1;
    private javax.swing.JMenuItem mniItemMovimientosCaja2;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCampanaSMS;
    private javax.swing.JMenu mnuConsultas;
    private javax.swing.JMenu mnuControlPresencia;
    private javax.swing.JMenuItem mnuItemArqueo1;
    private javax.swing.JMenuItem mnuItemArqueo2;
    private javax.swing.JMenuItem mnuItemArqueo3;
    private javax.swing.JMenuItem mnuItemArqueo4;
    private javax.swing.JMenuItem mnuItemArqueo5;
    private javax.swing.JMenuItem mnuItemCaja1;
    private javax.swing.JMenuItem mnuItemCaja3;
    private javax.swing.JMenuItem mnuItemCaja4;
    private javax.swing.JMenuItem mnuItemCaja5;
    private javax.swing.JMenuItem mnuItemCategoria;
    private javax.swing.JMenuItem mnuItemCategoria1;
    private javax.swing.JMenuItem mnuItemCategoria2;
    private javax.swing.JMenuItem mnuItemConsultaSocio1;
    private javax.swing.JMenuItem mnuItemConsultaSocio3;
    private javax.swing.JMenuItem mnuItemMarcajes;
    private javax.swing.JMenuItem mnuItemRecibos1;
    private javax.swing.JMenuItem mnuItemSalir;
    private javax.swing.JMenuItem mnuItemSobre;
    private javax.swing.JMenuItem mnuItemSocios;
    private javax.swing.JMenuItem mnuItemUsuarios;
    private javax.swing.JMenuItem mnuItemUsuarios1;
    private javax.swing.JMenuItem mnuItemUsuarios2;
    private javax.swing.JMenuItem mnuItemUsuarios3;
    private javax.swing.JMenu mnuMensualidades;
    private javax.swing.JMenu mnuRegistros;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuitemConsultaMensualidades;
    // End of variables declaration//GEN-END:variables
}
