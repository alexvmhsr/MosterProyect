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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "ARCHIVO")
public class Archivo implements Serializable {

    @Id
    @Column(name = "ID_ARCHIVO")
    private Integer id;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombre;
    @Column(name = "RUTA_ARCHIVO")
    private String ruta;
    @ManyToOne(optional = true)
    @JoinColumn(name = "ID_EXCURSION", referencedColumnName = "ID_EXCURSION", insertable = false, updatable = false)
    private Excursion excursion;

    public Archivo() {
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Excursion getExcursion() {
        return excursion;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Archivo other = (Archivo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Archivo{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + '}';
    }

}
