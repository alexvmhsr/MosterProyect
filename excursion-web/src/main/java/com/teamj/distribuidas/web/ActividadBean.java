/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Actividad;
import com.teamj.distribuidas.servicios.ActividadServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dennys
 */
@ViewScoped
@ManagedBean
public class ActividadBean implements Serializable{

    @EJB
    private ActividadServicio actividadServicio;
    private List<Actividad> actividades;
    private Actividad actividadSeleccionada;
    private String nombre;
    private String descripcion;

    

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad mochilaSeleccionada) {
        this.actividadSeleccionada = mochilaSeleccionada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @PostConstruct
    public void init() {
        actividades = actividadServicio.obtenerTodas();
    }
    
    public void agregarActividad() {
        //if (nombre != null && !nombre.isEmpty() && descripcion != null && !descripcion.isEmpty()) {
            Actividad m = new Actividad();
            m.setNombre(nombre);
            m.setId(null);
            m.setDescripcion(descripcion);
            actividadServicio.insertar(m);
            //actividades=actividadServicio.obtenerTodas();
        //}
    }

    public void eliminarActividad() {
        if (actividadSeleccionada != null) {
            actividadServicio.eliminar(actividadSeleccionada.getId());
        }
    }
    
    
}
