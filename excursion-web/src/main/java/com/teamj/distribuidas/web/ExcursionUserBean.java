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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Gaming
 */
@ManagedBean
@SessionScoped
public class ExcursionUserBean extends CrudBean implements Serializable {

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
    private List<ExcursionArticulo> excursionArticulosSelected;
    private ExcursionArticulo excursionArticulo;
    private Articulo articulo;
    private List<Articulo> articulos;
    private Articulo articuloSelected;
    private Integer cantidad;

    private List<UsuarioExcursion> participantes;
    private UsuarioExcursion participanteSeleccionado;

    private BigDecimal subtotal;
    private Integer totalArticulos;

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

    public List<ExcursionArticulo> getExcursionArticulosSelected() {
        return excursionArticulosSelected;
    }

    public void setExcursionArticulosSelected(List<ExcursionArticulo> excursionArticulosSelected) {
        this.excursionArticulosSelected = excursionArticulosSelected;
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

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotalArticulos(Integer totalArticulos) {
        this.totalArticulos = totalArticulos;
    }

    public Integer getTotalArticulos() {
        return totalArticulos;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    @PostConstruct
    public void init() {
        excursions = excursionServicio.obtenerTodas();
        this.excursion = new Excursion();
        this.cantidad = 1;
        this.subtotal = new BigDecimal(0);
        this.totalArticulos = 0;
        this.articulo = new Articulo();
        this.articulos = articuloServicio.obtenerTodas();
        this.excursionArticulos = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.excursionSelected = new Excursion();

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
                this.excursions.add(0, excursion);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La excursión ha sido creada correctamente"));
            } else {
                excursionServicio.actualizar(this.excursion);
                try {
                    BeanUtils.copyProperties(this.excursionSelected, this.excursion);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(ExcursionUserBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public void loadExcursionSelectedData(SelectEvent event) {
        // this.excursionArticulos = this.excursionSelected.getExcursionArticulos();
        this.excursionArticulos = ((Excursion) event.getObject()).getExcursionArticulos();
        this.participantes = ((Excursion) event.getObject()).getUsuarioExcursiones();
        try {
            BeanUtils.copyProperties(this.excursion, this.excursionSelected);
            // FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ExcursionUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.totalArticulos = 0;
        this.subtotal = new BigDecimal(BigInteger.ZERO);
        if (this.excursionArticulosSelected != null) {
            this.excursionArticulosSelected.clear();
        }
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
                this.excursionArticulo.getExcursionArticuloPK().setIdArticulo(articuloSelected.getId());
                this.excursionArticulo.setCantidad(this.cantidad);
                this.excursionArticuloServicio.insertar(this.excursionArticulo);
                this.excursionArticulo.setArticulo(articuloSelected);
                this.excursionArticulo.setExcursion(excursionSelected);
                this.excursionArticulos.add(0, excursionArticulo);
                this.cantidad = 1;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El artículo ha sido correctamente agregado"));
            }
        } catch (ValidationException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        RequestContext.getCurrentInstance().execute("PF('exc_art_dialog_var').hide()");
        this.reset();

    }

    public String duracion(Date salida, Date retorno) {
        int days = Days.daysBetween(new LocalDate(salida), new LocalDate(retorno)).getDays();
        return String.valueOf(days);
    }

    public void unselectDisabledRow(UnselectEvent e) {
        this.subtotal = new BigDecimal(BigInteger.ZERO);
        this.totalArticulos = 0;
        for (int i = excursionArticulosSelected.size() - 1; i >= 0; i--) {

            this.totalArticulos += excursionArticulosSelected.get(i).getCantidad();
            this.subtotal = this.subtotal.add(excursionArticulosSelected.get(i).getArticulo().getPrecio().multiply(new BigDecimal(excursionArticulosSelected.get(i).getCantidad())));

        }

    }

    public void selectDisabledRow(SelectEvent e) {
        this.subtotal = new BigDecimal(BigInteger.ZERO);
        this.totalArticulos = 0;
        for (int i = excursionArticulosSelected.size() - 1; i >= 0; i--) {
            if (excursionArticulosSelected.get(i).getArticulo().getStock() == 0) {
                this.excursionArticulosSelected.remove(i);

            } else {
                this.totalArticulos += excursionArticulosSelected.get(i).getCantidad();
                this.subtotal = this.subtotal.add(excursionArticulosSelected.get(i).getArticulo().getPrecio().multiply(new BigDecimal(excursionArticulosSelected.get(i).getCantidad())));
            }

        }

    }

    public void unselectDisabledRow(ToggleSelectEvent e) {
        this.subtotal = new BigDecimal(BigInteger.ZERO);
        this.totalArticulos = 0;
        for (int i = excursionArticulosSelected.size() - 1; i >= 0; i--) {
            if (excursionArticulosSelected.get(i).getArticulo().getStock() == 0) {
                this.excursionArticulosSelected.remove(i);

            } else {
                this.totalArticulos += excursionArticulosSelected.get(i).getCantidad();
                this.subtotal = this.subtotal.add(excursionArticulosSelected.get(i).getArticulo().getPrecio().multiply(new BigDecimal(excursionArticulosSelected.get(i).getCantidad())));
            }

        }

    }

}
