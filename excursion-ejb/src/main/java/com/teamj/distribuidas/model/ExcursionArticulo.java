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
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * You should incorporate the ParentObject reference just into ChildObject.Pk
 * rather than map parent and parentId separately:
 *
 * (getters, setters, Hibernate attributes not related to problem and member
 * access keywords omitted)
 *
 * class ChildObject {
 *
 * @Embeddable static class Pk {
 * @ManyToOne...
 * @JoinColumn(name="parentId") ParentObject parent;
 *
 * @Column... String name... ... }
 *
 * @EmbeddedId Pk id; } In ParentObject you then just put
 * @OneToMany(mappedBy="id.parent") and it works.
 * @author Dennys
 */
@Entity
@Table(name = "EXCURSION_ARTICULO")
public class ExcursionArticulo implements Serializable {

    @EmbeddedId
    ExcursionArticuloPK excursionArticuloPK;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "ID_EXCURSION", nullable = false,insertable = false,updatable = false)
    private Excursion excursion;

    @ManyToOne
    @JoinColumn(name = "ID_ARTICULO", nullable = false,insertable = false,updatable = false)
    private Articulo articulo;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.excursionArticuloPK);
        return hash;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
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
        return "ExcursionArticulo{" + "excursionArticuloPK=" + excursionArticuloPK + ", cantidad=" + cantidad + '}';
    }

}
