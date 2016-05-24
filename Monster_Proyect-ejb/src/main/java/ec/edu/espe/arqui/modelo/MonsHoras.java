/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "mons_horas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsHoras.findAll", query = "SELECT m FROM MonsHoras m"),
    @NamedQuery(name = "MonsHoras.findByHoraId", query = "SELECT m FROM MonsHoras m WHERE m.horaId = :horaId"),
    @NamedQuery(name = "MonsHoras.findByHoraRegFecha", query = "SELECT m FROM MonsHoras m WHERE m.horaRegFecha = :horaRegFecha"),
    @NamedQuery(name = "MonsHoras.findByHoraRegHoras", query = "SELECT m FROM MonsHoras m WHERE m.horaRegHoras = :horaRegHoras"),
    @NamedQuery(name = "MonsHoras.findByHoraDetalle", query = "SELECT m FROM MonsHoras m WHERE m.horaDetalle = :horaDetalle"),
    @NamedQuery(name = "MonsHoras.findByHoraSemana", query = "SELECT m FROM MonsHoras m WHERE m.horaSemana = :horaSemana"),
    @NamedQuery(name = "MonsHoras.findByHoraMes", query = "SELECT m FROM MonsHoras m WHERE m.horaMes = :horaMes"),
    @NamedQuery(name = "MonsHoras.findByHoraAnio", query = "SELECT m FROM MonsHoras m WHERE m.horaAnio = :horaAnio")})
public class MonsHoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_ID")
    private Integer horaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_REG_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaRegFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_REG_HORAS")
    private BigDecimal horaRegHoras;
    @Size(max = 200)
    @Column(name = "HORA_DETALLE")
    private String horaDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "HORA_SEMANA")
    private String horaSemana;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "HORA_MES")
    private String horaMes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORA_ANIO")
    private String horaAnio;
    @JoinColumns({
        @JoinColumn(name = "PROY_ID", referencedColumnName = "PROY_ID"),
        @JoinColumn(name = "EMPL_ID", referencedColumnName = "EMPL_ID")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MonsDetalleHoras monsDetalleHoras;

    public MonsHoras() {
    }

    public MonsHoras(Integer horaId) {
        this.horaId = horaId;
    }

    public MonsHoras(Integer horaId, Date horaRegFecha, BigDecimal horaRegHoras, String horaSemana, String horaMes, String horaAnio) {
        this.horaId = horaId;
        this.horaRegFecha = horaRegFecha;
        this.horaRegHoras = horaRegHoras;
        this.horaSemana = horaSemana;
        this.horaMes = horaMes;
        this.horaAnio = horaAnio;
    }

    public Integer getHoraId() {
        return horaId;
    }

    public void setHoraId(Integer horaId) {
        this.horaId = horaId;
    }

    public Date getHoraRegFecha() {
        return horaRegFecha;
    }

    public void setHoraRegFecha(Date horaRegFecha) {
        this.horaRegFecha = horaRegFecha;
    }

    public BigDecimal getHoraRegHoras() {
        return horaRegHoras;
    }

    public void setHoraRegHoras(BigDecimal horaRegHoras) {
        this.horaRegHoras = horaRegHoras;
    }

    public String getHoraDetalle() {
        return horaDetalle;
    }

    public void setHoraDetalle(String horaDetalle) {
        this.horaDetalle = horaDetalle;
    }

    public String getHoraSemana() {
        return horaSemana;
    }

    public void setHoraSemana(String horaSemana) {
        this.horaSemana = horaSemana;
    }

    public String getHoraMes() {
        return horaMes;
    }

    public void setHoraMes(String horaMes) {
        this.horaMes = horaMes;
    }

    public String getHoraAnio() {
        return horaAnio;
    }

    public void setHoraAnio(String horaAnio) {
        this.horaAnio = horaAnio;
    }

    public MonsDetalleHoras getMonsDetalleHoras() {
        return monsDetalleHoras;
    }

    public void setMonsDetalleHoras(MonsDetalleHoras monsDetalleHoras) {
        this.monsDetalleHoras = monsDetalleHoras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horaId != null ? horaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsHoras)) {
            return false;
        }
        MonsHoras other = (MonsHoras) object;
        if ((this.horaId == null && other.horaId != null) || (this.horaId != null && !this.horaId.equals(other.horaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsHoras[ horaId=" + horaId + " ]";
    }
    
}
