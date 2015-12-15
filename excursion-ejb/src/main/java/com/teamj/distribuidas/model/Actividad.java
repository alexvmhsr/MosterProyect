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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "ACTIVIDAD")
public class Actividad implements Serializable {

    @Size(max = 200)
    @Column(name = "ENCARGADO_ACTIVIDAD")
    private String encargadoActividad;

    @Id
    @Column(name = "ID_ACTIVIDAD", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE_ACTIVIDAD", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION_ACTIVIDAD")
    private String descripcion;

    @Column(name = "COSTO_ACTIVIDAD")
    private BigDecimal costo;

    public Actividad() {
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

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Actividad other = (Actividad) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", costo=" + costo + '}';
    }

    public String getEncargadoActividad() {
        return encargadoActividad;
    }

    public void setEncargadoActividad(String encargadoActividad) {
        this.encargadoActividad = encargadoActividad;
    }

}
