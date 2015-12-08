/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dennys
 */
@Embeddable
public class ItinerarioPK implements Serializable {
    

    @Column(name = "ID_ACTIVIDAD", nullable = false)
    private Integer idActividad;
    
    
    @Column(name = "ID_EXCURSION",nullable = false)
    private Integer idExcursion;
    
    
    @Column(name = "ID_SITIO",nullable = false)
    private Integer idSitio;

    public ItinerarioPK() {
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Integer idExcursion) {
        this.idExcursion = idExcursion;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idActividad);
        hash = 29 * hash + Objects.hashCode(this.idExcursion);
        hash = 29 * hash + Objects.hashCode(this.idSitio);
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
        final ItinerarioPK other = (ItinerarioPK) obj;
        if (!Objects.equals(this.idActividad, other.idActividad)) {
            return false;
        }
        if (!Objects.equals(this.idExcursion, other.idExcursion)) {
            return false;
        }
        if (!Objects.equals(this.idSitio, other.idSitio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItinerarioPK{" + "idActividad=" + idActividad + ", idExcursion=" + idExcursion + ", idSitio=" + idSitio + '}';
    }

   
    
}
