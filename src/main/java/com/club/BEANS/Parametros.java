/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.BEANS;

/**
 *
 * @author Diego Noble
 */
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "parametros")

public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "url_post_cobrosya")
    private String urlPostCobrosYa;
    @Column(name = "url_consulta_cobranzas_cobrosya")
    private String urlConsultaCobranzasCobrosYa;
    @Column(name = "token_cobrosya")
    private String tokenCobrosYa;
    private String casilla_email;
    private String psw_email;
    private String usuario_SMS;
    private String psw_SMS;
    @JoinColumn(name = "idcobrador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cobrador cobradorCobrosYa;
    @Column(name = "api_url_crear")
    private String apiUrlCrear;
    private String emailPadron;
    private Integer toleranciaDiasAtraso;
    @JoinColumn(name = "id_rubroPagoCuotasCampEco", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rubro rubroPagoCuotasCampEco;
    
    @JoinColumn(name = "id_sectorCampEco", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sectores sectorCampEco;

    private String MySql_Path;
    private String nombreBasesDatos;

    @JoinColumn(name = "id_rubroVentas", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rubro rubroVentas;
    private Integer idWebCam;
    
    
    public Parametros() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlPostCobrosYa() {
        return urlPostCobrosYa;
    }

    public void setUrlPostCobrosYa(String urlPostCobrosYa) {
        this.urlPostCobrosYa = urlPostCobrosYa;
    }

    public String getTokenCobrosYa() {
        return tokenCobrosYa;
    }

    public void setTokenCobrosYa(String tokenCobrosYa) {
        this.tokenCobrosYa = tokenCobrosYa;
    }

    public String getCasilla_email() {
        return casilla_email;
    }

    public void setCasilla_email(String casilla_email) {
        this.casilla_email = casilla_email;
    }

    public String getPsw_email() {
        return psw_email;
    }

    public void setPsw_email(String psw_email) {
        this.psw_email = psw_email;
    }

    public String getUsuario_SMS() {
        return usuario_SMS;
    }

    public void setUsuario_SMS(String usuario_SMS) {
        this.usuario_SMS = usuario_SMS;
    }

    public String getPsw_SMS() {
        return psw_SMS;
    }

    public void setPsw_SMS(String psw_SMS) {
        this.psw_SMS = psw_SMS;
    }

    public Cobrador getCobradorCobrosYa() {
        return cobradorCobrosYa;
    }

    public void setCobradorCobrosYa(Cobrador cobradorCobrosYa) {
        this.cobradorCobrosYa = cobradorCobrosYa;
    }

    public String getApiUrlCrear() {
        return apiUrlCrear;
    }

    public void setApiUrlCrear(String apiUrlCrear) {
        this.apiUrlCrear = apiUrlCrear;
    }

    public String getEmailPadron() {
        return emailPadron;
    }

    public void setEmailPadron(String emailPadron) {
        this.emailPadron = emailPadron;
    }

    public String getUrlConsultaCobranzasCobrosYa() {
        return urlConsultaCobranzasCobrosYa;
    }

    public void setUrlConsultaCobranzasCobrosYa(String urlConsultaCobranzasCobrosYa) {
        this.urlConsultaCobranzasCobrosYa = urlConsultaCobranzasCobrosYa;
    }

    public Integer getToleranciaDiasAtraso() {
        return toleranciaDiasAtraso;
    }

    public void setToleranciaDiasAtraso(Integer toleranciaDiasAtraso) {
        this.toleranciaDiasAtraso = toleranciaDiasAtraso;
    }

   
    public Rubro getRubroPagoCuotasCampEco() {
        return rubroPagoCuotasCampEco;
    }

    public void setRubroPagoCuotasCampEco(Rubro rubroPagoCuotasCampEco) {
        this.rubroPagoCuotasCampEco = rubroPagoCuotasCampEco;
    }

    public String getMySql_Path() {
        return MySql_Path;
    }

    public void setMySql_Path(String MySql_Path) {
        this.MySql_Path = MySql_Path;
    }

    public String getNombreBasesDatos() {
        return nombreBasesDatos;
    }

    public void setNombreBasesDatos(String nombreBasesDatos) {
        this.nombreBasesDatos = nombreBasesDatos;
    }

    public Sectores getSectorCampEco() {
        return sectorCampEco;
    }

    public void setSectorCampEco(Sectores sectorCampEco) {
        this.sectorCampEco = sectorCampEco;
    }

    public Rubro getRubroVentas() {
        return rubroVentas;
    }

    public void setRubroVentas(Rubro rubroVentas) {
        this.rubroVentas = rubroVentas;
    }

    public Integer getIdWebCam() {
        return idWebCam;
    }

    public void setIdWebCam(Integer idWebCam) {
        this.idWebCam = idWebCam;
    }

    
    
    
    
}
