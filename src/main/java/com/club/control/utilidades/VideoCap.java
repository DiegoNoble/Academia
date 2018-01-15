/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.control.utilidades;

import com.club.BEANS.Parametros;
import com.club.DAOs.ParametrosDAO;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class VideoCap {

    JPanel panelVideo;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    VideoCapture cap;
    Mat2Image mat2Img = new Mat2Image();

    public VideoCap(JPanel panelVideo) {
        this.panelVideo = panelVideo;
        ParametrosDAO parametrosDAO = new ParametrosDAO();
        Parametros param = (Parametros) parametrosDAO.BuscaPorID(Parametros.class, 1);
        cap = new VideoCapture();
        cap.open(param.getIdWebCam());
        cap.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, panelVideo.getWidth());
        cap.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, panelVideo.getHeight());
    }

    public BufferedImage getOneFrame() {
        cap.read(mat2Img.mat);
        return mat2Img.getImage(mat2Img.mat);
    }
}
