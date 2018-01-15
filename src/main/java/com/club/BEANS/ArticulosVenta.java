package com.club.BEANS;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "articulos_venta")
public class ArticulosVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @ManyToOne(targetEntity = Articulo.class)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @Column(name = "descuento")
    private String descuento;
    @Column(name = "valor")
    private Double valor;
    private Integer posicion;

    public ArticulosVenta(Integer posicion, Articulo articulo, Double cantidad) {
        this.posicion = posicion;
        this.articulo = articulo;
        this.cantidad = cantidad;

        this.valor = cantidad * (articulo.getValor_venta());
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public ArticulosVenta() {
    }

    public ArticulosVenta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

}
