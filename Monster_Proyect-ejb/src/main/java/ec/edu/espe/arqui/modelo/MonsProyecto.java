/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PabloA
 */
@Entity
@Table(name = "mons_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsProyecto.findAll", query = "SELECT m FROM MonsProyecto m"),
    @NamedQuery(name = "MonsProyecto.findByProyId", query = "SELECT m FROM MonsProyecto m WHERE m.proyId = :proyId"),
    @NamedQuery(name = "MonsProyecto.findByProyNumero", query = "SELECT m FROM MonsProyecto m WHERE m.proyNumero = :proyNumero"),
    @NamedQuery(name = "MonsProyecto.findByProyNombre", query = "SELECT m FROM MonsProyecto m WHERE m.proyNombre = :proyNombre"),
    @NamedQuery(name = "MonsProyecto.findByProyDetalle", query = "SELECT m FROM MonsProyecto m WHERE m.proyDetalle = :proyDetalle")})
public class MonsProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROY_ID")
    private Integer proyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PROY_NUMERO")
    private String proyNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROY_NOMBRE")
    private String proyNombre;
    @Size(max = 200)
    @Column(name = "PROY_DETALLE")
    private String proyDetalle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsProyecto", fetch = FetchType.LAZY)
    private List<MonsDetalleHoras> monsDetalleHorasList;
    @JoinColumn(name = "EMPL_ID", referencedColumnName = "EMPL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsEmpleado emplId;
    @JoinColumn(name = "LUGA_ID", referencedColumnName = "LUGA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsLugar lugaId;
    @JoinColumn(name = "DEPA_ID", referencedColumnName = "DEPA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsDepartamento depaId;

    public MonsProyecto() {
    }

    public MonsProyecto(Integer proyId) {
        this.proyId = proyId;
    }

    public MonsProyecto(Integer proyId, String proyNumero, String proyNombre) {
        this.proyId = proyId;
        this.proyNumero = proyNumero;
        this.proyNombre = proyNombre;
    }

    public Integer getProyId() {
        return proyId;
    }

    public void setProyId(Integer proyId) {
        this.proyId = proyId;
    }

    public String getProyNumero() {
        return proyNumero;
    }

    public void setProyNumero(String proyNumero) {
        this.proyNumero = proyNumero;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public String getProyDetalle() {
        return proyDetalle;
    }

    public void setProyDetalle(String proyDetalle) {
        this.proyDetalle = proyDetalle;
    }

    @XmlTransient
    public List<MonsDetalleHoras> getMonsDetalleHorasList() {
        return monsDetalleHorasList;
    }

    public void setMonsDetalleHorasList(List<MonsDetalleHoras> monsDetalleHorasList) {
        this.monsDetalleHorasList = monsDetalleHorasList;
    }

    public MonsEmpleado getEmplId() {
        return emplId;
    }

    public void setEmplId(MonsEmpleado emplId) {
        this.emplId = emplId;
    }

    public MonsLugar getLugaId() {
        return lugaId;
    }

    public void setLugaId(MonsLugar lugaId) {
        this.lugaId = lugaId;
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
        hash += (proyId != null ? proyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsProyecto)) {
            return false;
        }
        MonsProyecto other = (MonsProyecto) object;
        if ((this.proyId == null && other.proyId != null) || (this.proyId != null && !this.proyId.equals(other.proyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsProyecto[ proyId=" + proyId + " ]";
    }
    
}
