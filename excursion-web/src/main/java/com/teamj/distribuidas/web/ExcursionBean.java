/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.servicios.ExcursionServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class ExcursionBean extends CrudBean implements Serializable {

    @EJB
    private ExcursionServicio excursionServicio;

    private List<Excursion> excursions;
    private Excursion excursionSelected;
    private String nombre;
    private String descripcion;
    private Excursion excursion;

    private List<ExcursionArticulo> excursionArticulos;
    private ExcursionArticulo excursionArticuloSelected;

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

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    public void setExcursionSelected(Excursion excursionSelected) {
        this.excursionSelected = excursionSelected;
    }

    public Excursion getExcursionSelected() {
        return excursionSelected;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public List<ExcursionArticulo> getExcursionArticulos() {
        return excursionArticulos;
    }

    public void setExcursionArticulos(List<ExcursionArticulo> excursionArticulos) {
        this.excursionArticulos = excursionArticulos;
    }

    @PostConstruct
    public void init() {
        excursions = excursionServicio.obtenerTodas();
        this.excursion = new Excursion();
        

    }

    public void beginCreation() {
        this.excursion = new Excursion();
        this.beginCreate();
    }

    public void deleteExcursion() {

        excursionServicio.eliminar(excursionSelected.getId());
        this.excursions.remove(excursionSelected);

    }

    public void beginModification() {
        this.beginModify();
        this.excursion = new Excursion();
        try {
            BeanUtils.copyProperties(this.excursion, this.excursionSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void beginView() {
        this.reset();
        this.excursion = null;
        this.excursion = new Excursion();
        try {
            BeanUtils.copyProperties(this.excursion, this.excursionSelected);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void createOrUpdate() {
        if (validateFields()) {
            if (this.isCreating()) {
                excursionServicio.insertar(this.excursion);
                this.excursions.add(0, excursion);
            } else {
                excursionServicio.actualizar(this.excursion);
                try {
                    BeanUtils.copyProperties(this.excursionSelected, this.excursion);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(ExcursionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            RequestContext.getCurrentInstance().execute("PF('agregar_dialog_var').hide()");
            this.reset();
        }
        // this.excursionSelected=null;

    }

    public String todayDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public boolean validateFields() {
        if (this.excursion.getFechaLimite() != null && this.excursion.getFechaRetorno() != null && this.excursion.getFechaSalida() != null) {
            if (!(this.excursion.getFechaLimite().before(this.excursion.getFechaSalida())
                    && this.excursion.getFechaSalida().before(this.excursion.getFechaRetorno()))) {
                // MessageUtil.showMessage("Las fechas no son consecuentes en el tiempo", 
                //       "La fecha límite debe ser menor a la fecha fecha de salida, y la de salida menor a la de retorno",
                //     FacesMessage.SEVERITY_ERROR);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las fechas no son consecuentes en el tiempo", "La fecha límite debe ser menor a la fecha fecha de salida, y la de salida menor a la de retorno"));
                return false;
            }
        }
        return true;
    }

    public void validateDates() {
        if (this.excursion != null) {

            if (this.excursion.getFechaLimite() != null && this.excursion.getFechaRetorno() != null && this.excursion.getFechaSalida() != null) {
                if (!(this.excursion.getFechaLimite().before(this.excursion.getFechaSalida())
                        && this.excursion.getFechaRetorno().before(this.excursion.getFechaSalida()))) {
                    // MessageUtil.showMessage("Las fechas no son consecuentes en el tiempo", 
                    //       "La fecha límite debe ser menor a la fecha fecha de salida, y la de salida menor a la de retorno",
                    //     FacesMessage.SEVERITY_ERROR);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las fechas no son consecuentes en el tiempo", "La fecha límite debe ser menor a la fecha fecha de salida, y la de salida menor a la de retorno"));
                }
            }
        }
    }

    public void loadExcursionSelectedData() {
     //   this.excursionArticulos = this.excursionServicio.obtenerArticulos(excursionSelected);
    }
}
