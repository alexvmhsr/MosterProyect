/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Comentario;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.servicios.ComentarioServicio;
import com.teamj.distribuidas.servicios.ExcursionServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Gaming
 */
@ViewScoped
@ManagedBean
public class ComentarioBean extends CrudBean implements Serializable {
    @EJB
    private ComentarioServicio comentarioServicio;
    private ExcursionServicio excursionServicio;
    private List<Comentario> comentarios;
    private List<Excursion> Excursiones;
    private Comentario comentarioSelected;
    private Excursion excursionSelected;
    private String nombre;
    private String descripcion;
    private Comentario comentario;
    private Excursion excursion;
    public ComentarioServicio getComentarioServicio() {
        return comentarioServicio;
    }

    public void setComentarioServicio(ComentarioServicio comentarioServicio) {
        this.comentarioServicio = comentarioServicio;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario getComentarioSelected() {
        return comentarioSelected;
    }

    public void setComentarioSelected(Comentario comentarioSelected) {
        this.comentarioSelected = comentarioSelected;
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

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    
    @PostConstruct
    public void init() {
        comentarios = comentarioServicio.obtenerTodas();
        //Excursiones = excursionServicio.obtenerTodas();
    }

    public void beginCreation() {
        this.comentario = new Comentario();
        this.beginCreate();
    }

    public void deleteMochila() {

        comentarioServicio.eliminar(comentarioSelected.getId());
        this.comentarios.remove(comentarioSelected);

    }

    public void beginModification() {
        this.beginModify();
        this.comentario = new Comentario();
        try {
            BeanUtils.copyProperties(this.comentario, this.comentarioSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void beginView() {
        this.reset();
        this.comentario = null;
        this.comentario = new Comentario();
        try {
            BeanUtils.copyProperties(this.comentario, this.comentarioSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void createOrUpdate() {
        if (this.isCreating()) {
            comentarioServicio.insertar(this.comentario);
            this.comentarios.add(0, comentario);

        } else {
            comentarioServicio.actualizar(this.comentario);
            try {
                BeanUtils.copyProperties(this.comentarioSelected, this.comentario);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(MochilaBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        RequestContext.getCurrentInstance().execute("PF('agregar_dialog_var').hide()");
        this.reset();
        // this.mochilaSelected=null;

    }

    public List<Excursion> getExcursiones() {
        return Excursiones;
    }

    public void setExcursiones(List<Excursion> Excursiones) {
        this.Excursiones = Excursiones;
    }

    public Excursion getExcursionSelected() {
        return excursionSelected;
    }

    public void setExcursionSelected(Excursion excursionSelected) {
        this.excursionSelected = excursionSelected;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    
    
}
