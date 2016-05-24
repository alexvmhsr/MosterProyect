/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PabloA
 */
@Entity
@Table(name = "mons_director")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsDirector.findAll", query = "SELECT m FROM MonsDirector m"),
    @NamedQuery(name = "MonsDirector.findByDireId", query = "SELECT m FROM MonsDirector m WHERE m.direId = :direId"),
    @NamedQuery(name = "MonsDirector.findByDireFecha", query = "SELECT m FROM MonsDirector m WHERE m.direFecha = :direFecha"),
    @NamedQuery(name = "MonsDirector.findByDireDetalle", query = "SELECT m FROM MonsDirector m WHERE m.direDetalle = :direDetalle")})
public class MonsDirector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DIRE_ID")
    private Integer direId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIRE_FECHA")
    @Temporal(TemporalType.DATE)
    private Date direFecha;
    @Size(max = 200)
    @Column(name = "DIRE_DETALLE")
    private String direDetalle;
    @JoinColumn(name = "EMPL_ID", referencedColumnName = "EMPL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsEmpleado emplId;
    @JoinColumn(name = "DEPA_ID", referencedColumnName = "DEPA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MonsDepartamento depaId;

    public MonsDirector() {
    }

    public MonsDirector(Integer direId) {
        this.direId = direId;
    }

    public MonsDirector(Integer direId, Date direFecha) {
        this.direId = direId;
        this.direFecha = direFecha;
    }

    public Integer getDireId() {
        return direId;
    }

    public void setDireId(Integer direId) {
        this.direId = direId;
    }

    public Date getDireFecha() {
        return direFecha;
    }

    public void setDireFecha(Date direFecha) {
        this.direFecha = direFecha;
    }

    public String getDireDetalle() {
        return direDetalle;
    }

    public void setDireDetalle(String direDetalle) {
        this.direDetalle = direDetalle;
    }

    public MonsEmpleado getEmplId() {
        return emplId;
    }

    public void setEmplId(MonsEmpleado emplId) {
        this.emplId = emplId;
    }

    public MonsDepartamento getDepaId() {
        return depaId;
    }

    public void setDepaId(MonsDepartamento depaId) {
        this.depaId = depaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (direId != null ? direId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsDirector)) {
            return false;
        }
        MonsDirector other = (MonsDirector) object;
        if ((this.direId == null && other.direId != null) || (this.direId != null && !this.direId.equals(other.direId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsDirector[ direId=" + direId + " ]";
    }
    
}
