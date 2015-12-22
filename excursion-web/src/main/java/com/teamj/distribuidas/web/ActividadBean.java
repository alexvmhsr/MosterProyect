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
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dennys
 */
@ViewScoped
@ManagedBean
public class ActividadBean extends CrudBean implements Serializable{

    @EJB
    private ActividadServicio actividadServicio;
    private List<Actividad> actividades;
    private Actividad actividadSeleccionada;
    private String nombre;
    private String descripcion;
    private Actividad actividad;
    

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
    public void beginCreation() {
        this.actividad = new Actividad();
        this.beginCreate();
    }
    public void deleteActividad() {

        actividadServicio.eliminar(actividadSeleccionada.getId());
        this.actividades.remove(actividadSeleccionada);

    }

    public void beginModification() {
        this.beginModify();
        this.actividad = new Actividad();
        try {
            BeanUtils.copyProperties(this.actividad, this.actividadSeleccionada);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void beginView() {
        this.reset();
        this.actividad = null;
        this.actividad = new Actividad();
        try {
            BeanUtils.copyProperties(this.actividad, this.actividadSeleccionada);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void createOrUpdate() {
        if (this.isCreating()) {
            actividadServicio.insertar(this.actividad);
            this.actividades.add(0, actividad);

        } else {
            actividadServicio.actualizar(this.actividad);
            try {
                BeanUtils.copyProperties(this.actividadSeleccionada, this.actividad);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(MochilaBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        RequestContext.getCurrentInstance().execute("PF('agregar_dialog_var').hide()");
        this.reset();
        // this.mochilaSelected=null;

    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
