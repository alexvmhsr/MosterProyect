/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.model.ExcursionArticuloPK;
import com.teamj.distribuidas.model.UsuarioExcursion;
import com.teamj.distribuidas.servicios.ArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Gaming
 */
@ManagedBean
@ViewScoped
public class ExcursionBean extends CrudBean implements Serializable {

    @EJB
    private ExcursionServicio excursionServicio;

    @EJB
    private ArticuloServicio articuloServicio;

    @EJB
    private ExcursionArticuloServicio excursionArticuloServicio;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private List<Excursion> excursions;
    private Excursion excursionSelected;
    private String nombre;
    private String descripcion;
    private Excursion excursion;

    private List<ExcursionArticulo> excursionArticulos;
    private ExcursionArticulo excursionArticuloSelected;
    private ExcursionArticulo excursionArticulo;
    private Articulo articulo;
    private List<Articulo> articulos;
    private Articulo articuloSelected;
    private Integer cantidad;

    private List<UsuarioExcursion> participantes;
    private UsuarioExcursion participanteSeleccionado;

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

    public ExcursionArticulo getExcursionArticuloSelected() {
        return excursionArticuloSelected;
    }

    public void setExcursionArticuloSelected(ExcursionArticulo excursionArticuloSelected) {
        this.excursionArticuloSelected = excursionArticuloSelected;
    }

    public void setExcursionArticulo(ExcursionArticulo excursionArticulo) {
        this.excursionArticulo = excursionArticulo;
    }

    public ExcursionArticulo getExcursionArticulo() {
        return excursionArticulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticuloSelected(Articulo articuloSelected) {
        this.articuloSelected = articuloSelected;
    }

    public Articulo getArticuloSelected() {
        return articuloSelected;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setExcursionArticuloServicio(ExcursionArticuloServicio excursionArticuloServicio) {
        this.excursionArticuloServicio = excursionArticuloServicio;
    }

    public ExcursionArticuloServicio getExcursionArticuloServicio() {
        return excursionArticuloServicio;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public void setParticipantes(List<UsuarioExcursion> participantes) {
        this.participantes = participantes;
    }

    public void setParticipanteSeleccionado(UsuarioExcursion participanteSeleccionado) {
        this.participanteSeleccionado = participanteSeleccionado;
    }

    public UsuarioExcursion getParticipanteSeleccionado() {
        return participanteSeleccionado;
    }

    public List<UsuarioExcursion> getParticipantes() {
        return participantes;
    }

    @PostConstruct
    public void init() {
        excursions = excursionServicio.obtenerTodas();
        this.excursion = new Excursion();
        this.cantidad = 1;
        this.articulo = new Articulo();
        this.articulos = articuloServicio.obtenerTodas();
        this.excursionArticulos = new ArrayList<>();
        this.participantes = new ArrayList<>();

    }

    public void beginCreation() {
        this.excursion = new Excursion();
        this.beginCreate();
    }

    public void deleteExcursion() {

        excursionServicio.eliminar(excursionSelected.getId());
        this.excursions.remove(excursionSelected);
        this.excursionSelected = null;
        this.excursionArticuloSelected = null;
        this.excursionArticulos.clear();
        //    this.
        this.participantes.clear();

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

        } catch (IllegalAccessException | InvocationTargetException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }

    }

    public void createOrUpdate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (validateFields()) {
            if (this.isCreating()) {
                excursionServicio.insertar(this.excursion, this.sessionBean.getUser());
                //Excursion e = this.excursionServicio.obtenerPorId(excursion.getId());
                //if (e != null) {
                // this.excursions.add(0, this.excursion); por el momento
                this.excursions = this.excursionServicio.obtenerTodas();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La excursión ha sido creada correctamente"));
                //}
            } else {
                excursionServicio.actualizar(this.excursion);
                try {
                    BeanUtils.copyProperties(this.excursionSelected, this.excursion);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(ExcursionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.excursionArticuloSelected = null;
            this.excursionSelected = null;

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
            if (this.excursion.getMaxAsistentes() < this.excursion.getMinAsistentes()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación de asistentes falló", "El número de asistentes máximo debe ser mayor al número de asistentes mínimo"));
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

    public void loadExcursionSelectedData(SelectEvent event) {
        // this.excursionArticulos = this.excursionSelected.getExcursionArticulos();
        this.excursionArticulos = ((Excursion) event.getObject()).getExcursionArticulos();
        this.participantes = ((Excursion) event.getObject()).getUsuarioExcursiones();
        for (int i = (excursionArticulos.size() - 1); i >= 0; i--) {
            if (excursionArticulos.get(i).getArticulo().getNombre().startsWith("Derecho a")) {
                excursionArticulos.remove(i);
            }
        }
        // FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void beginExcursionArticuloCreation() {
        this.excursionArticulo = new ExcursionArticulo();
        ExcursionArticuloPK excursionArticuloPK = new ExcursionArticuloPK();
        excursionArticuloPK.setIdExcursion(this.excursionSelected.getId());

        this.excursionArticulo.setExcursionArticuloPK(excursionArticuloPK);
        //this.excursionArticulo.setExcursion(excursionSelected);
        this.articulo = new Articulo();
        this.beginCreate();
    }

    public void createOrUpdateArticuloExcursion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            if (this.isCreating()) {
                boolean isNew = true;
                int oldQuantity = 0;
                for (ExcursionArticulo ea : this.excursionSelected.getExcursionArticulos()) {
                    if (ea.getArticulo().equals(articuloSelected)) {
                        oldQuantity = ea.getCantidad();
                        isNew = false;
                        break;
                    }
                }
                this.excursionArticulo.getExcursionArticuloPK().setIdArticulo(articuloSelected.getId());
                if (isNew) {
                    this.excursionArticulo.setCantidad(this.cantidad);
                    this.excursionArticuloServicio.insertar(this.excursionArticulo);
                } else {
                    this.excursionArticulo.setCantidad(this.cantidad + oldQuantity);
                    this.excursionArticuloServicio.actualizar(this.excursionArticulo);

                }
                this.excursionArticulo.setArticulo(articuloSelected);
                this.excursionArticulo.setExcursion(excursionSelected);
                if (isNew) {
                    this.excursionArticulos.add(0, excursionArticulo);
                } else {
                    for (ExcursionArticulo ea : this.excursionArticulos) {
                        if (ea.getArticulo().equals(articuloSelected)) {
                            ea.setCantidad(ea.getCantidad() + this.cantidad);
                            break;
                        }
                    }
                }
                this.cantidad = 1;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El artículo ha sido correctamente agregado"));
            }
        } catch (ValidationException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        RequestContext.getCurrentInstance().execute("PF('exc_art_dialog_var').hide()");
        this.reset();

    }

    public void deleteArticuloDeExcursion() {
        this.excursionArticuloServicio.eliminar(excursionArticuloSelected.getExcursionArticuloPK());
        this.excursionArticulos.remove(excursionArticuloSelected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El artículo ha sido eliminado correctamente "));
    }

}
