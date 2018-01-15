/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.views;

import com.club.control.utilidades.VideoCap;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Diego
 */
public class WebCam extends javax.swing.JDialog {

    VideoCap videoCap;
    Thread thread;
    BufferedImage imagen;
    File tempFile;

    public WebCam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);

        videoCap = new VideoCap(VIDEO);
        thread = new Thread(new CapturaVideo());
        thread.start();

    }

    public class CapturaVideo implements Runnable {

        @Override
        public void run() {
            while (true) {

                try {
                    Graphics g = VIDEO.getGraphics();
                    imagen = videoCap.getOneFrame();
                    if (g != null) {
                        g.drawImage(imagen, 0, 0, WebCam.this);
                    }

                    Thread.sleep(30);
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public File getTempFile() {
        return tempFile;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        VIDEO = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 380));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        VIDEO.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout VIDEOLayout = new javax.swing.GroupLayout(VIDEO);
        VIDEO.setLayout(VIDEOLayout);
        VIDEOLayout.setHorizontalGroup(
            VIDEOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        VIDEOLayout.setVerticalGroup(
            VIDEOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(VIDEO, gridBagConstraints);

        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIniciar.setText("Foto");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnIniciar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {
            tempFile = new File(System.getProperty("java.io.tmpdir") + "foto.jpg");
            ImageIO.write(imagen, "jpg", tempFile);

            thread.interrupt();
            this.
                    dispose();
        } catch (IOException ex) {
            Logger.getLogger(WebCam.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnIniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel VIDEO;
    private javax.swing.JButton btnIniciar;
    // End of variables declaration//GEN-END:variables
}
