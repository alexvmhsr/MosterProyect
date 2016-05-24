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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "mons_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsDepartamento.findAll", query = "SELECT m FROM MonsDepartamento m"),
    @NamedQuery(name = "MonsDepartamento.findByDepaId", query = "SELECT m FROM MonsDepartamento m WHERE m.depaId = :depaId"),
    @NamedQuery(name = "MonsDepartamento.findByDepaNombre", query = "SELECT m FROM MonsDepartamento m WHERE m.depaNombre = :depaNombre"),
    @NamedQuery(name = "MonsDepartamento.findByDepaNumero", query = "SELECT m FROM MonsDepartamento m WHERE m.depaNumero = :depaNumero"),
    @NamedQuery(name = "MonsDepartamento.findByDepaDetalle", query = "SELECT m FROM MonsDepartamento m WHERE m.depaDetalle = :depaDetalle")})
public class MonsDepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEPA_ID")
    private Integer depaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DEPA_NOMBRE")
    private String depaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DEPA_NUMERO")
    private String depaNumero;
    @Size(max = 150)
    @Column(name = "DEPA_DETALLE")
    private String depaDetalle;
    @JoinTable(name = "mons_detalle_lugar", joinColumns = {
        @JoinColumn(name = "DEPA_ID", referencedColumnName = "DEPA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "LUGA_ID", referencedColumnName = "LUGA_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<MonsLugar> monsLugarList;
    @OneToMany(mappedBy = "depaId", fetch = FetchType.LAZY)
    private List<MonsEmpleado> monsEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depaId", fetch = FetchType.LAZY)
    private List<MonsDirector> monsDirectorList;
    @OneToMany(mappedBy = "depaId", fetch = FetchType.LAZY)
    private List<MonsProyecto> monsProyectoList;

    public MonsDepartamento() {
    }

    public MonsDepartamento(Integer depaId) {
        this.depaId = depaId;
    }

    public MonsDepartamento(Integer depaId, String depaNombre, String depaNumero) {
        this.depaId = depaId;
        this.depaNombre = depaNombre;
        this.depaNumero = depaNumero;
    }

    public Integer getDepaId() {
        return depaId;
    }

    public void setDepaId(Integer depaId) {
        this.depaId = depaId;
    }

    public String getDepaNombre() {
        return depaNombre;
    }

    public void setDepaNombre(String depaNombre) {
        this.depaNombre = depaNombre;
    }

    public String getDepaNumero() {
        return depaNumero;
    }

    public void setDepaNumero(String depaNumero) {
        this.depaNumero = depaNumero;
    }

    public String getDepaDetalle() {
        return depaDetalle;
    }

    public void setDepaDetalle(String depaDetalle) {
        this.depaDetalle = depaDetalle;
    }

    @XmlTransient
    public List<MonsLugar> getMonsLugarList() {
        return monsLugarList;
    }

    public void setMonsLugarList(List<MonsLugar> monsLugarList) {
        this.monsLugarList = monsLugarList;
    }

    @XmlTransient
    public List<MonsEmpleado> getMonsEmpleadoList() {
        return monsEmpleadoList;
    }

    public void setMonsEmpleadoList(List<MonsEmpleado> monsEmpleadoList) {
        this.monsEmpleadoList = monsEmpleadoList;
    }

    @XmlTransient
    public List<MonsDirector> getMonsDirectorList() {
        return monsDirectorList;
    }

    public void setMonsDirectorList(List<MonsDirector> monsDirectorList) {
        this.monsDirectorList = monsDirectorList;
    }

    @XmlTransient
    public List<MonsProyecto> getMonsProyectoList() {
        return monsProyectoList;
    }

    public void setMonsProyectoList(List<MonsProyecto> monsProyectoList) {
        this.monsProyectoList = monsProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depaId != null ? depaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsDepartamento)) {
            return false;
        }
        MonsDepartamento other = (MonsDepartamento) object;
        if ((this.depaId == null && other.depaId != null) || (this.depaId != null && !this.depaId.equals(other.depaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsDepartamento[ depaId=" + depaId + " ]";
    }
    
}
