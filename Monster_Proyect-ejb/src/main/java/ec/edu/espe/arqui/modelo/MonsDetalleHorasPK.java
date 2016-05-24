/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author PabloA
 */
@Embeddable
public class MonsDetalleHorasPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROY_ID")
    private int proyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPL_ID")
    private int emplId;

    public MonsDetalleHorasPK() {
    }

    public MonsDetalleHorasPK(int proyId, int emplId) {
        this.proyId = proyId;
        this.emplId = emplId;
    }

    public int getProyId() {
        return proyId;
    }

    public void setProyId(int proyId) {
        this.proyId = proyId;
    }

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proyId;
        hash += (int) emplId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsDetalleHorasPK)) {
            return false;
        }
        MonsDetalleHorasPK other = (MonsDetalleHorasPK) object;
        if (this.proyId != other.proyId) {
            return false;
        }
        if (this.emplId != other.emplId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsDetalleHorasPK[ proyId=" + proyId + ", emplId=" + emplId + " ]";
    }
    
}
