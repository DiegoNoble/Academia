/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.BEANS;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "tbmensualidades")

public class Mensualidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VALOR")
    private Double valor;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(name = "PAGO")
    private Boolean pago = false;
    
    @JoinColumn(name = "ID_SOCIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Socio socio;

    private Boolean enviado = false;
    private String situacionTalonCobrosYa;
    @Column(name = "nro_talon")
    private String nroTalonCobrosYa;
    @Column(name = "id_secreto")
    private String idSecreto;
    @Column(name = "url_pdf")
    private String urlPDF;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaHoraTransaccionCobrosYa;
    private Integer medioPagoId;
    private String medioPago;

    public Mensualidades() {
    }

    public Mensualidades(Integer id) {
        this.id = id;
    }

    public Mensualidades(Integer id, Double valor, Date fechaVencimiento, Date fechaPago, Boolean pago,  Socio tbsocio) {
        this.id = id;
        this.valor = valor;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaPago = fechaPago;
        this.pago = pago;
        this.socio = tbsocio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public String getSituacionTalonCobrosYa() {
        return situacionTalonCobrosYa;
    }

    public void setSituacionTalonCobrosYa(String situacionTalonCobrosYa) {
        this.situacionTalonCobrosYa = situacionTalonCobrosYa;
    }

    public String getNroTalonCobrosYa() {
        return nroTalonCobrosYa;
    }

    public void setNroTalonCobrosYa(String nroTalonCobrosYa) {
        this.nroTalonCobrosYa = nroTalonCobrosYa;
    }

    public String getIdSecreto() {
        return idSecreto;
    }

    public void setIdSecreto(String idSecreto) {
        this.idSecreto = idSecreto;
    }

    public String getUrlPDF() {
        return urlPDF;
    }

    public void setUrlPDF(String urlPDF) {
        this.urlPDF = urlPDF;
    }

    public Date getFechaHoraTransaccionCobrosYa() {
        return fechaHoraTransaccionCobrosYa;
    }

    public void setFechaHoraTransaccionCobrosYa(Date fechaHoraTransaccionCobrosYa) {
        this.fechaHoraTransaccionCobrosYa = fechaHoraTransaccionCobrosYa;
    }

    public Integer getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(Integer medioPagoId) {
        this.medioPagoId = medioPagoId;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensualidades other = (Mensualidades) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensalidade " + id + ", socio=" + socio;
    }

    
}
