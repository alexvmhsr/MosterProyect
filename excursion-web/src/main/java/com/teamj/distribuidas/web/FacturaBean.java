/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.model.Detalle;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.model.Factura;
import com.teamj.distribuidas.servicios.ArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionServicio;
import com.teamj.distribuidas.servicios.FacturaServicio;
import com.teamj.distribuidas.servicios.UsuarioServicio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
@ViewScoped
public class FacturaBean extends CrudBean implements Serializable {

    @EJB
    private ExcursionServicio excursionServicio;

    @EJB
    private ArticuloServicio articuloServicio;

    @EJB
    private FacturaServicio facturaServicio;

    @EJB
    private UsuarioServicio usuarioServicio;

    @EJB
    private ExcursionArticuloServicio excursionArticuloServicio;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private String nombre;
    private String descripcion;
    private List<ExcursionArticulo> excursionArticulos;
    private List<ExcursionArticulo> excursionArticulosSelected;
    private ExcursionArticulo excursionArticulo;
    private Articulo articulo;
    private List<Detalle> detalles;
    private Detalle detalleSelected;
    private Integer cantidad;
    private Factura facturaMochila;
    private Factura factura;
    private BigDecimal subtotal;
    private Integer totalArticulos;
    private BigDecimal totalFactura;

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

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public Detalle getDetalleSelected() {
        return detalleSelected;
    }

    public void setDetalleSelected(Detalle detalleSelected) {
        this.detalleSelected = detalleSelected;
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

    public Factura getFacturaMochila() {
        return facturaMochila;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setFacturaMochila(Factura facturaMochila) {
        this.facturaMochila = facturaMochila;
    }

    public BigDecimal getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(BigDecimal totalFactura) {
        this.totalFactura = totalFactura;
    }

    public void init() {
        this.cantidad = 1;
        this.subtotal = new BigDecimal(0);
        this.totalArticulos = 0;
        this.articulo = new Articulo();
        this.facturaMochila = this.facturaServicio.encontrarMochilaFactura(this.sessionBean.getUser().getId());
        if (this.facturaMochila != null && facturaMochila.getDetalles() != null) {
            this.detalles = facturaMochila.getDetalles();
        }
        this.factura = new Factura();
        this.factura.setDocEmisor("1723520662001");
        this.factura.setTipoDocEmisor("04");
        this.factura.setTipoDocReceptor("04");
        this.factura.setFechaEmision(new Date());
        this.factura.setRazonSocial("ExcursionesTOGO");
        this.factura.setSecuencial("000000000000001");
        this.factura.setDetalles(this.detalles);
    }

    public void createOrUpdate() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (this.isCreating()) {
            //            excursionServicio.insertar(this.excursion, this.sessionBean.getUser());
            //          this.excursions.add(0, excursion);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La excursión ha sido creada correctamente"));
        } else {
            // excursionServicio.actualizar(this.excursion);
//                try {
//             //       BeanUtils.copyProperties(this.excursionSelected, this.excursion);
//                } catch (IllegalAccessException | InvocationTargetException ex) {
//                    Logger.getLogger(MochilaCompraBean.class.getName()).log(Level.SEVERE, null, ex);
//                }

        }
        RequestContext.getCurrentInstance().execute("PF('agregar_dialog_var').hide()");
        this.reset();

        // this.excursionSelected=null;
    }

    public String todayDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public void beginExcursionArticuloCreation() {

    }

    public void createOrUpdateArticuloExcursion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.isCreating()) {
                this.excursionArticulo.getExcursionArticuloPK().setIdArticulo(detalleSelected.getId());
                this.excursionArticulo.setCantidad(this.cantidad);
                this.excursionArticuloServicio.insertar(this.excursionArticulo);
//                this.excursionArticulo.setArticulo(detalleSelected);
                //            this.excursionArticulo.setExcursion(excursionSelected);
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
