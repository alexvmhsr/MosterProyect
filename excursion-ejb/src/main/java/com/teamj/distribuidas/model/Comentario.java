/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "Comentario")
public class Comentario implements Serializable {

    @Id
    @Column(name = "ID_COMENTARIO", nullable = false)
    private Integer id;

    @Column(name = "ID_EXCURSION", nullable = false)
    private Integer idExcursion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ID_EXCURSION", referencedColumnName = "ID_EXCURSION", insertable = false, updatable = false)
    private Excursion excursion;

    @Column(name = "CUERPO_COMENTARIO")
    private String cuerpo;

    @Column(name = "AUTOR_COMENTARIO")
    private String autor;

    @Column(name = "MAIL_COMENTARIO")
    private String mail;

    @Column(name = "PUNTUACION_COMENTARIO")
    private BigDecimal puntuacion;

    public Comentario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Integer idExcursion) {
        this.idExcursion = idExcursion;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public BigDecimal getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigDecimal puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", idExcursion=" + idExcursion + ", excursion=" + excursion + ", cuerpo=" + cuerpo + ", autor=" + autor + ", mail=" + mail + ", puntuacion=" + puntuacion + '}';
    }

}
