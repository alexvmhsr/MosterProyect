/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 *
 * @author Dennys
 */
@Embeddable
public class ExcursionArticuloPK implements Serializable {
    
    @Column(name = "ID_EXCURSION")
    private Integer idExcursion;
    
    @Column(name = "ID_ARTICULO")
    private Integer idArticulo;

    public ExcursionArticuloPK() {
    }

    public Integer getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Integer idExcursion) {
        this.idExcursion = idExcursion;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExcursion != null ? idExcursion.hashCode() : 0);
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExcursionArticuloPK)) {
            return false;
        }
        ExcursionArticuloPK other = (ExcursionArticuloPK) object;
        if ((this.idExcursion == null && other.getIdExcursion() != null) || (this.getIdExcursion() != null && !this.getIdExcursion().equals(other.getIdExcursion()))) {
            return false;
        }
        if ((this.idArticulo == null && other.getIdArticulo() != null) || (this.idArticulo != null && !this.idArticulo.equals(other.getIdArticulo()))) {
            return false;
        }
        return true;
    }

    
}
