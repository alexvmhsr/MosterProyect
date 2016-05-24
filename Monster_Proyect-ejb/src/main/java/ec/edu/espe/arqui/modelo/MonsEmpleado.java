/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PabloA
 */
@Entity
@Table(name = "mons_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsEmpleado.findAll", query = "SELECT m FROM MonsEmpleado m"),
    @NamedQuery(name = "MonsEmpleado.findByEmplId", query = "SELECT m FROM MonsEmpleado m WHERE m.emplId = :emplId"),
    @NamedQuery(name = "MonsEmpleado.findByEmplNombre", query = "SELECT m FROM MonsEmpleado m WHERE m.emplNombre = :emplNombre"),
    @NamedQuery(name = "MonsEmpleado.findByEmplSeguro", query = "SELECT m FROM MonsEmpleado m WHERE m.emplSeguro = :emplSeguro"),
    @NamedQuery(name = "MonsEmpleado.findByEmplDireccion", query = "SELECT m FROM MonsEmpleado m WHERE m.emplDireccion = :emplDireccion"),
    @NamedQuery(name = "MonsEmpleado.findByEmplSalario", query = "SELECT m FROM MonsEmpleado m WHERE m.emplSalario = :emplSalario"),
    @NamedQuery(name = "MonsEmpleado.findByEmplSexo", query = "SELECT m FROM MonsEmpleado m WHERE m.emplSexo = :emplSexo"),
    @NamedQuery(name = "MonsEmpleado.findByEmplFecnac", query = "SELECT m FROM MonsEmpleado m WHERE m.emplFecnac = :emplFecnac")})
public class MonsEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPL_ID")
    private Integer emplId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMPL_NOMBRE")
    private String emplNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EMPL_SEGURO")
    private String emplSeguro;
    @Size(max = 100)
    @Column(name = "EMPL_DIRECCION")
    private String emplDireccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPL_SALARIO")
    private BigDecimal emplSalario;
    @Column(name = "EMPL_SEXO")
    private Character emplSexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPL_FECNAC")
    @Temporal(TemporalType.DATE)
    private Date emplFecnac;
    @OneToMany(mappedBy = "emplId", fetch = FetchType.LAZY)
    private List<MonsFamiliar> monsFamiliarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsEmpleado", fetch = FetchType.LAZY)
    private List<MonsDetalleHoras> monsDetalleHorasList;
    @JoinColumn(name = "DEPA_ID", referencedColumnName = "DEPA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MonsDepartamento depaId;
    @OneToMany(mappedBy = "emplId", fetch = FetchType.LAZY)
    private List<MonsDirector> monsDirectorList;
    @OneToMany(mappedBy = "emplId", fetch = FetchType.LAZY)
    private List<MonsProyecto> monsProyectoList;

    public MonsEmpleado() {
    }

    public MonsEmpleado(Integer emplId) {
        this.emplId = emplId;
    }

    public MonsEmpleado(Integer emplId, String emplNombre, String emplSeguro, BigDecimal emplSalario, Date emplFecnac) {
        this.emplId = emplId;
        this.emplNombre = emplNombre;
        this.emplSeguro = emplSeguro;
        this.emplSalario = emplSalario;
        this.emplFecnac = emplFecnac;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public String getEmplNombre() {
        return emplNombre;
    }

    public void setEmplNombre(String emplNombre) {
        this.emplNombre = emplNombre;
    }

    public String getEmplSeguro() {
        return emplSeguro;
    }

    public void setEmplSeguro(String emplSeguro) {
        this.emplSeguro = emplSeguro;
    }

    public String getEmplDireccion() {
        return emplDireccion;
    }

    public void setEmplDireccion(String emplDireccion) {
        this.emplDireccion = emplDireccion;
    }

    public BigDecimal getEmplSalario() {
        return emplSalario;
    }

    public void setEmplSalario(BigDecimal emplSalario) {
        this.emplSalario = emplSalario;
    }

    public Character getEmplSexo() {
        return emplSexo;
    }

    public void setEmplSexo(Character emplSexo) {
        this.emplSexo = emplSexo;
    }

    public Date getEmplFecnac() {
        return emplFecnac;
    }

    public void setEmplFecnac(Date emplFecnac) {
        this.emplFecnac = emplFecnac;
    }

    @XmlTransient
    public List<MonsFamiliar> getMonsFamiliarList() {
        return monsFamiliarList;
    }

    public void setMonsFamiliarList(List<MonsFamiliar> monsFamiliarList) {
        this.monsFamiliarList = monsFamiliarList;
    }

    @XmlTransient
    public List<MonsDetalleHoras> getMonsDetalleHorasList() {
        return monsDetalleHorasList;
    }

    public void setMonsDetalleHorasList(List<MonsDetalleHoras> monsDetalleHorasList) {
        this.monsDetalleHorasList = monsDetalleHorasList;
    }

    public MonsDepartamento getDepaId() {
        return depaId;
    }

    public void setDepaId(MonsDepartamento depaId) {
        this.depaId = depaId;
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
        hash += (emplId != null ? emplId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsEmpleado)) {
            return false;
        }
        MonsEmpleado other = (MonsEmpleado) object;
        if ((this.emplId == null && other.emplId != null) || (this.emplId != null && !this.emplId.equals(other.emplId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsEmpleado[ emplId=" + emplId + " ]";
    }
    
}
