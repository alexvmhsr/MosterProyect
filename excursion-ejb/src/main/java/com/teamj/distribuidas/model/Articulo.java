/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "ARTICULO")
public class Articulo implements Serializable {

    @Id
    @SequenceGenerator(name = "ARTICULO_SEQ1", sequenceName = "ARTICULO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ARTICULO_SEQ1")
    @Column(name = "ID_ARTICULO")
    private Integer id;

    @Column(name = "NOMBRE_ARTICULO")
    private String nombre;

    @Column(name = "DESCRIPCION_ARTICULO")
    private String descripcion;

    @Column(name = "PRECIO_ARTICULO")
    private BigDecimal precio;

    @Column(name = "STOCK")
    private Integer stock;

    public Articulo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Articulo other = (Articulo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }

}
