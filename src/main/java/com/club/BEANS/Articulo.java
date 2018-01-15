package com.club.BEANS;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "valor_venta")
    private Double valor_venta;
    @Column(name = "valor_compra")
    private Double valor_compra;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne(targetEntity = CategoriaArticulo.class)
    @JoinColumn(name = "categoriaArticulo")
    private CategoriaArticulo categoriaArticulo;
    @ManyToOne(targetEntity = Unidad.class)
    @JoinColumn(name = "unidad")
    private Unidad unidad;
    
    @Enumerated(EnumType.STRING)
    private SituacionArticuloEnum activo = SituacionArticuloEnum.ACTIVO;

    public Articulo() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor_venta() {
        return valor_venta;
    }

    /**
     * @param valor_venta the valor_venta to set
     */
    public void setValor_venta(Double valor_venta) {
        this.valor_venta = valor_venta;
    }

    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaArticulo getCategoriaArticulo() {
        return categoriaArticulo;
    }

    public void setCategoriaArticulo(CategoriaArticulo categoriaArticulo) {
        this.categoriaArticulo = categoriaArticulo;
    }

    /**
     * @return the iva
     */
    /**
     * @return the valor_compra_impuesto
     */
    public Double getValor_compra() {
        return valor_compra;
    }

    /**
     * @param valor_compra_impuesto the valor_compra_impuesto to set
     */
    public void setValor_compra(Double valor_compra_impuesto) {
        this.valor_compra = valor_compra_impuesto;
    }

    /**
     * @return the unidad
     */
    public Unidad getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public SituacionArticuloEnum getActivo() {
        return activo;
    }

    public void setActivo(SituacionArticuloEnum activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return id;
    }

}
