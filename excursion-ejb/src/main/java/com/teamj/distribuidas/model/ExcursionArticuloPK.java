/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;
import java.math.BigInteger;
import javax.persistence.Column;
/**
 *
 * @author Dennys
 */
public class ExcursionArticuloPK {
    
    @Column(name = "ID_EXCURSION")
    private BigInteger idExcursion;
    
    @Column(name = "ID_ARTICULO")
    private BigInteger idArticulo;

    public ExcursionArticuloPK() {
    }

    public ExcursionArticuloPK(BigInteger idExcursion, BigInteger idArticulo) {
        this.idExcursion = idExcursion;
        this.idArticulo = idArticulo;
    }

    public BigInteger getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(BigInteger idExcursion) {
        this.idExcursion = idExcursion;
    }

    public BigInteger getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(BigInteger idArticulo) {
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
