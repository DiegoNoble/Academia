
package com.club.BEANS;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "categoria_articulo")

public class CategoriaArticulo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;

    public CategoriaArticulo() {
    }

        
    public CategoriaArticulo(String nombre) {
        this.nombre = nombre;
    }

    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString(){
        return nombre;
    }
}
