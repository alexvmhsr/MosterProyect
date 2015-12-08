/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;

/**
 *
 * @author Dennys
 */
public class ExcursionArticulo {

    @EmbeddedId
    ExcursionArticuloPK excursionArticuloPK;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "descuento")
    private BigDecimal descuento;

    public ExcursionArticulo() {
    }

    public ExcursionArticuloPK getExcursionArticuloPK() {
        return excursionArticuloPK;
    }

    public void setExcursionArticuloPK(ExcursionArticuloPK excursionArticuloPK) {
        this.excursionArticuloPK = excursionArticuloPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.excursionArticuloPK);
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
        final ExcursionArticulo other = (ExcursionArticulo) obj;
        if (!Objects.equals(this.excursionArticuloPK, other.excursionArticuloPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExcursionArticulo{" + "excursionArticuloPK=" + excursionArticuloPK + ", cantidad=" + cantidad + ", descuento=" + descuento + '}';
    }

}
