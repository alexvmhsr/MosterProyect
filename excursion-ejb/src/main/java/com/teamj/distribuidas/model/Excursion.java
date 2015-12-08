/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Dennys
 */
@Entity(name = "EXCURSION")
public class Excursion {

    @Id
    @Column(name = "ID_EXCURSION")
    private Integer id;

    @Column(name = "NOMBRE_EXCURSION")
    private String nombre;

    @Column(name = "PUNTUACION_EXCURSION")
    private String puntuacion;

    @Column(name = "DESTINO_EXCURSION")
    private String destino;

    @Column(name = "RIESGOS_EXCURSION")
    private String riesgos;

    @Column(name = "OBJETIVO_EXCURSION")
    private String objetivo;

    @Column(name = "OBSERVACIONES_EXCURSION")
    private String observaciones;

    @Column(name = "COSTO_EXCURSION")
    private BigDecimal costo;

    @OneToMany(mappedBy = "excursion", targetEntity = UsuarioExcursion.class,
            fetch = FetchType.EAGER)
    private List<UsuarioExcursion> usuarioExcursiones;

    @OneToMany(mappedBy = "excursion", targetEntity = Itinerario.class,
            fetch = FetchType.EAGER)
    private List<Itinerario> itinerarios;

    @OneToMany(mappedBy = "excursion", targetEntity = Comentario.class,
            fetch = FetchType.LAZY)
    private List<Comentario> comentarios;
    
    @OneToMany(mappedBy = "excursion", targetEntity = Archivo.class,
            fetch = FetchType.LAZY)
    private List<Archivo> archivos;

    public Excursion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(String riesgos) {
        this.riesgos = riesgos;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public List<UsuarioExcursion> getUsuarioExcursiones() {
        return usuarioExcursiones;
    }

    public void setUsuarioExcursiones(List<UsuarioExcursion> usuarioExcursiones) {
        this.usuarioExcursiones = usuarioExcursiones;
    }

    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<Itinerario> itinerarios) {
        this.itinerarios = itinerarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Excursion other = (Excursion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Excursion{" + "id=" + id + ", nombre=" + nombre + ", puntuacion=" + puntuacion + ", destino=" + destino + ", riesgos=" + riesgos + ", objetivo=" + objetivo + ", observaciones=" + observaciones + ", costo=" + costo + '}';
    }

}
