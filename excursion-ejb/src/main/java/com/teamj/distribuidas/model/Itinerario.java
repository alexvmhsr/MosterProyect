/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dennys
 */
@Entity(name = "ITINERARIO")
public class Itinerario implements Serializable {

    @EmbeddedId
    protected ItinerarioPK itinerarioPK;
    
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    
    @Column(name = "FECHA_RETORNO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetorno;

    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    
    @JoinColumn(name = "ID_SITIO", referencedColumnName = "ID_SITIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sitio sitio;
    
    @JoinColumn(name = "ID_EXCURSION", referencedColumnName = "ID_EXCURSION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Excursion excursion;
    
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;

    public Itinerario() {
    }

    public ItinerarioPK getItinerarioPK() {
        return itinerarioPK;
    }

    public void setItinerarioPK(ItinerarioPK itinerarioPK) {
        this.itinerarioPK = itinerarioPK;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.itinerarioPK);
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
        final Itinerario other = (Itinerario) obj;
        if (!Objects.equals(this.itinerarioPK, other.itinerarioPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Itinerario{" + "itinerarioPK=" + itinerarioPK + ", fechaSalida=" + fechaSalida + ", fechaRetorno=" + fechaRetorno + ", descuento=" + descuento + ", sitio=" + sitio + ", excursion=" + excursion + ", actividad=" + actividad + '}';
    }

}
