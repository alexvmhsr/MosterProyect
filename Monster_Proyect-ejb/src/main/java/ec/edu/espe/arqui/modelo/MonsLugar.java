/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "mons_lugar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsLugar.findAll", query = "SELECT m FROM MonsLugar m"),
    @NamedQuery(name = "MonsLugar.findByLugaId", query = "SELECT m FROM MonsLugar m WHERE m.lugaId = :lugaId"),
    @NamedQuery(name = "MonsLugar.findByLugaCiiudad", query = "SELECT m FROM MonsLugar m WHERE m.lugaCiiudad = :lugaCiiudad"),
    @NamedQuery(name = "MonsLugar.findByLugaSector", query = "SELECT m FROM MonsLugar m WHERE m.lugaSector = :lugaSector"),
    @NamedQuery(name = "MonsLugar.findByLugaNombre", query = "SELECT m FROM MonsLugar m WHERE m.lugaNombre = :lugaNombre"),
    @NamedQuery(name = "MonsLugar.findByLugaTorre", query = "SELECT m FROM MonsLugar m WHERE m.lugaTorre = :lugaTorre"),
    @NamedQuery(name = "MonsLugar.findByLugaPiso", query = "SELECT m FROM MonsLugar m WHERE m.lugaPiso = :lugaPiso"),
    @NamedQuery(name = "MonsLugar.findByLugaZona", query = "SELECT m FROM MonsLugar m WHERE m.lugaZona = :lugaZona")})
public class MonsLugar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LUGA_ID")
    private Integer lugaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LUGA_CIIUDAD")
    private String lugaCiiudad;
    @Size(max = 100)
    @Column(name = "LUGA_SECTOR")
    private String lugaSector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LUGA_NOMBRE")
    private String lugaNombre;
    @Size(max = 10)
    @Column(name = "LUGA_TORRE")
    private String lugaTorre;
    @Size(max = 10)
    @Column(name = "LUGA_PISO")
    private String lugaPiso;
    @Size(max = 50)
    @Column(name = "LUGA_ZONA")
    private String lugaZona;
    @ManyToMany(mappedBy = "monsLugarList", fetch = FetchType.LAZY)
    private List<MonsDepartamento> monsDepartamentoList;
    @OneToMany(mappedBy = "lugaId", fetch = FetchType.LAZY)
    private List<MonsProyecto> monsProyectoList;

    public MonsLugar() {
    }

    public MonsLugar(Integer lugaId) {
        this.lugaId = lugaId;
    }

    public MonsLugar(Integer lugaId, String lugaCiiudad, String lugaNombre) {
        this.lugaId = lugaId;
        this.lugaCiiudad = lugaCiiudad;
        this.lugaNombre = lugaNombre;
    }

    public Integer getLugaId() {
        return lugaId;
    }

    public void setLugaId(Integer lugaId) {
        this.lugaId = lugaId;
    }

    public String getLugaCiiudad() {
        return lugaCiiudad;
    }

    public void setLugaCiiudad(String lugaCiiudad) {
        this.lugaCiiudad = lugaCiiudad;
    }

    public String getLugaSector() {
        return lugaSector;
    }

    public void setLugaSector(String lugaSector) {
        this.lugaSector = lugaSector;
    }

    public String getLugaNombre() {
        return lugaNombre;
    }

    public void setLugaNombre(String lugaNombre) {
        this.lugaNombre = lugaNombre;
    }

    public String getLugaTorre() {
        return lugaTorre;
    }

    public void setLugaTorre(String lugaTorre) {
        this.lugaTorre = lugaTorre;
    }

    public String getLugaPiso() {
        return lugaPiso;
    }

    public void setLugaPiso(String lugaPiso) {
        this.lugaPiso = lugaPiso;
    }

    public String getLugaZona() {
        return lugaZona;
    }

    public void setLugaZona(String lugaZona) {
        this.lugaZona = lugaZona;
    }

    @XmlTransient
    public List<MonsDepartamento> getMonsDepartamentoList() {
        return monsDepartamentoList;
    }

    public void setMonsDepartamentoList(List<MonsDepartamento> monsDepartamentoList) {
        this.monsDepartamentoList = monsDepartamentoList;
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
        hash += (lugaId != null ? lugaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsLugar)) {
            return false;
        }
        MonsLugar other = (MonsLugar) object;
        if ((this.lugaId == null && other.lugaId != null) || (this.lugaId != null && !this.lugaId.equals(other.lugaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsLugar[ lugaId=" + lugaId + " ]";
    }
    
}
