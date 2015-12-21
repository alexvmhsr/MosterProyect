/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Mochila;
import com.teamj.distribuidas.servicios.MochilaServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Dennys
 */
@ViewScoped
@ManagedBean
public class MochilaBean extends CrudBean implements Serializable {

    @EJB
    private MochilaServicio mochilaServicio;
    private List<Mochila> mochilas;
    private Mochila mochilaSelected;
    private String nombre;
    private String descripcion;
    private Mochila mochila;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mochila> getMochilas() {
        return mochilas;
    }

    public void setMochilas(List<Mochila> mochilas) {
        this.mochilas = mochilas;
    }

    public void setMochilaSelected(Mochila mochilaSelected) {
        this.mochilaSelected = mochilaSelected;
    }

    public Mochila getMochilaSelected() {
        return mochilaSelected;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }

    @PostConstruct
    public void init() {
        mochilas = mochilaServicio.obtenerTodas();
    }

    public void beginCreation() {
        this.mochila = new Mochila();
        this.beginCreate();
    }

    public void deleteMochila() {

        mochilaServicio.eliminar(mochilaSelected.getId());
        this.mochilas.remove(mochilaSelected);

    }

    public void beginModification() {
        this.beginModify();
        this.mochila = new Mochila();
        try {
            BeanUtils.copyProperties(this.mochila, this.mochilaSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }
    public void beginView() {
        this.reset();
        this.mochila = new Mochila();
        try {
            BeanUtils.copyProperties(this.mochila, this.mochilaSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void createOrUpdate() {
        if (this.isCreating()) {
            mochilaServicio.insertar(this.mochila);
            this.mochilas.add(0, mochila);
        } else {
            mochilaServicio.actualizar(this.mochila);
            try {
                BeanUtils.copyProperties(this.mochilaSelected, this.mochila);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(MochilaBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            

        }

    }
}
