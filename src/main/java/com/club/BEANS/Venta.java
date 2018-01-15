package com.club.BEANS;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private Double total;
    private Double saldo;

    @ManyToOne(targetEntity = Socio.class)
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @Column(name = "moneda")
    @Enumerated(EnumType.STRING)
    private MonedaEnum moneda;
    @Column(name = "nro")
    private Integer NroDocumento;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticulosVenta> articulosVenta;

    private String observaciones;
    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum tipoDocumentoEnum;

    public Venta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ArticulosVenta> getArticulosVenta() {
        return articulosVenta;
    }

    public void setArticulosVenta(List<ArticulosVenta> articulosVenta) {
        this.articulosVenta = articulosVenta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Integer getNroDocumento() {
        return NroDocumento;
    }

    public void setNroDocumento(Integer NroDocumento) {
        this.NroDocumento = NroDocumento;
    }

    public MonedaEnum getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaEnum moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TipoDocumentoEnum getTipoDocumentoEnum() {
        return tipoDocumentoEnum;
    }

    public void setTipoDocumentoEnum(TipoDocumentoEnum tipoDocumentoEnum) {
        this.tipoDocumentoEnum = tipoDocumentoEnum;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
