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
@Table(name = "mons_familiar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsFamiliar.findAll", query = "SELECT m FROM MonsFamiliar m"),
    @NamedQuery(name = "MonsFamiliar.findByFamiId", query = "SELECT m FROM MonsFamiliar m WHERE m.famiId = :famiId"),
    @NamedQuery(name = "MonsFamiliar.findByFamiNombre", query = "SELECT m FROM MonsFamiliar m WHERE m.famiNombre = :famiNombre"),
    @NamedQuery(name = "MonsFamiliar.findByFamiParentezco", query = "SELECT m FROM MonsFamiliar m WHERE m.famiParentezco = :famiParentezco"),
    @NamedQuery(name = "MonsFamiliar.findByFamiFechanacimiento", query = "SELECT m FROM MonsFamiliar m WHERE m.famiFechanacimiento = :famiFechanacimiento"),
    @NamedQuery(name = "MonsFamiliar.findByFamiDetalle", query = "SELECT m FROM MonsFamiliar m WHERE m.famiDetalle = :famiDetalle")})
public class MonsFamiliar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FAMI_ID")
    private Integer famiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FAMI_NOMBRE")
    private String famiNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FAMI_PARENTEZCO")
    private String famiParentezco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAMI_FECHANACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date famiFechanacimiento;
    @Size(max = 200)
    @Column(name = "FAMI_DETALLE")
    private String famiDetalle;
    @JoinColumn(name = "EMPL_ID", referencedColumnName = "EMPL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsEmpleado emplId;

    public MonsFamiliar() {
    }

    public MonsFamiliar(Integer famiId) {
        this.famiId = famiId;
    }

    public MonsFamiliar(Integer famiId, String famiNombre, String famiParentezco, Date famiFechanacimiento) {
        this.famiId = famiId;
        this.famiNombre = famiNombre;
        this.famiParentezco = famiParentezco;
        this.famiFechanacimiento = famiFechanacimiento;
    }

    public Integer getFamiId() {
        return famiId;
    }

    public void setFamiId(Integer famiId) {
        this.famiId = famiId;
    }

    public String getFamiNombre() {
        return famiNombre;
    }

    public void setFamiNombre(String famiNombre) {
        this.famiNombre = famiNombre;
    }

    public String getFamiParentezco() {
        return famiParentezco;
    }

    public void setFamiParentezco(String famiParentezco) {
        this.famiParentezco = famiParentezco;
    }

    public Date getFamiFechanacimiento() {
        return famiFechanacimiento;
    }

    public void setFamiFechanacimiento(Date famiFechanacimiento) {
        this.famiFechanacimiento = famiFechanacimiento;
    }

    public String getFamiDetalle() {
        return famiDetalle;
    }

    public void setFamiDetalle(String famiDetalle) {
        this.famiDetalle = famiDetalle;
    }

    public MonsEmpleado getEmplId() {
        return emplId;
    }

    public void setEmplId(MonsEmpleado emplId) {
        this.emplId = emplId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (famiId != null ? famiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsFamiliar)) {
            return false;
        }
        MonsFamiliar other = (MonsFamiliar) object;
        if ((this.famiId == null && other.famiId != null) || (this.famiId != null && !this.famiId.equals(other.famiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsFamiliar[ famiId=" + famiId + " ]";
    }
    
}
