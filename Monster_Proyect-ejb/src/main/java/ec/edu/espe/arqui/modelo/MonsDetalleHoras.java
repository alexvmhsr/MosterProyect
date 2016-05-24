/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PabloA
 */
@Entity
@Table(name = "mons_detalle_horas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsDetalleHoras.findAll", query = "SELECT m FROM MonsDetalleHoras m"),
    @NamedQuery(name = "MonsDetalleHoras.findByProyId", query = "SELECT m FROM MonsDetalleHoras m WHERE m.monsDetalleHorasPK.proyId = :proyId"),
    @NamedQuery(name = "MonsDetalleHoras.findByEmplId", query = "SELECT m FROM MonsDetalleHoras m WHERE m.monsDetalleHorasPK.emplId = :emplId")})
public class MonsDetalleHoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsDetalleHorasPK monsDetalleHorasPK;
    @JoinColumn(name = "PROY_ID", referencedColumnName = "PROY_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MonsProyecto monsProyecto;
    @JoinColumn(name = "EMPL_ID", referencedColumnName = "EMPL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MonsEmpleado monsEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsDetalleHoras", fetch = FetchType.LAZY)
    private List<MonsHoras> monsHorasList;

    public MonsDetalleHoras() {
    }

    public MonsDetalleHoras(MonsDetalleHorasPK monsDetalleHorasPK) {
        this.monsDetalleHorasPK = monsDetalleHorasPK;
    }

    public MonsDetalleHoras(int proyId, int emplId) {
        this.monsDetalleHorasPK = new MonsDetalleHorasPK(proyId, emplId);
    }

    public MonsDetalleHorasPK getMonsDetalleHorasPK() {
        return monsDetalleHorasPK;
    }

    public void setMonsDetalleHorasPK(MonsDetalleHorasPK monsDetalleHorasPK) {
        this.monsDetalleHorasPK = monsDetalleHorasPK;
    }

    public MonsProyecto getMonsProyecto() {
        return monsProyecto;
    }

    public void setMonsProyecto(MonsProyecto monsProyecto) {
        this.monsProyecto = monsProyecto;
    }

    public MonsEmpleado getMonsEmpleado() {
        return monsEmpleado;
    }

    public void setMonsEmpleado(MonsEmpleado monsEmpleado) {
        this.monsEmpleado = monsEmpleado;
    }

    @XmlTransient
    public List<MonsHoras> getMonsHorasList() {
        return monsHorasList;
    }

    public void setMonsHorasList(List<MonsHoras> monsHorasList) {
        this.monsHorasList = monsHorasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsDetalleHorasPK != null ? monsDetalleHorasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsDetalleHoras)) {
            return false;
        }
        MonsDetalleHoras other = (MonsDetalleHoras) object;
        if ((this.monsDetalleHorasPK == null && other.monsDetalleHorasPK != null) || (this.monsDetalleHorasPK != null && !this.monsDetalleHorasPK.equals(other.monsDetalleHorasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsDetalleHoras[ monsDetalleHorasPK=" + monsDetalleHorasPK + " ]";
    }
    
}
