/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.servicios.ArticuloServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gaming
 */
@ManagedBean
@SessionScoped
public class ArticuloBean extends CrudBean implements Serializable {

    @EJB
    private ArticuloServicio articuloServicio;

    private List<Articulo> articulos;
    private Articulo articuloSelected;
    private String nombre;
    private String descripcion;
    private Articulo articulo;

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

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void setArticuloSelected(Articulo articuloSelected) {
        this.articuloSelected = articuloSelected;
    }

    public Articulo getArticuloSelected() {
        return articuloSelected;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @PostConstruct
    public void init() {
        articulos = articuloServicio.obtenerTodas();
        this.articulo = new Articulo();

    }

    public void beginCreation() {
        this.articulo = new Articulo();
        this.beginCreate();
    }

    public void deleteArticulo() {

        articuloServicio.eliminar(articuloSelected.getId());
        this.articulos.remove(articuloSelected);

    }

    public void beginModification() {
        this.beginModify();
        this.articulo = new Articulo();
        try {
            BeanUtils.copyProperties(this.articulo, this.articuloSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

   

    public void createOrUpdate() {
        if (this.isCreating()) {
            articuloServicio.insertar(this.articulo);
            this.articulos.add(0, articulo);
        } else {
            try {
            articuloServicio.actualizar(this.articulo);
                BeanUtils.copyProperties(this.articuloSelected, this.articulo);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(ArticuloBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestContext.getCurrentInstance().execute("PF('articulo_dialog_var').hide()");
        this.reset();
    }
}
