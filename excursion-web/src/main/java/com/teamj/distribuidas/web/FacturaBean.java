/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.model.Detalle;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.model.Factura;
import com.teamj.distribuidas.servicios.ArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionArticuloServicio;
import com.teamj.distribuidas.servicios.ExcursionServicio;
import com.teamj.distribuidas.servicios.FacturaServicio;
import com.teamj.distribuidas.servicios.UsuarioServicio;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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

    @ManagedProperty(value = "#{emailSessionBean}")
    private EmailSessionBean emailSessionBean;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private String nombre;
    private String descripcion;

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
    private StreamedContent content;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

    public void setEmailSessionBean(EmailSessionBean emailSessionBean) {
        this.emailSessionBean = emailSessionBean;
    }

    public EmailSessionBean getEmailSessionBean() {
        return emailSessionBean;
    }

    public void init() {
        this.cantidad = 1;
        this.subtotal = new BigDecimal(0);
        this.totalFactura = BigDecimal.ZERO;
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
        if (this.detalles != null) {
            for (Detalle d : this.detalles) {
                this.totalFactura = this.totalFactura.add(new BigDecimal(d.getArticulo().getPrecio().floatValue() * (float) d.getCantidad()));
            }
        }
        //iva
        this.subtotal = totalFactura;
        this.totalFactura = this.totalFactura.add(new BigDecimal(this.totalFactura.floatValue() * 0.12f));

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

    public String duracion(Date salida, Date retorno) {
        int days = Days.daysBetween(new LocalDate(salida), new LocalDate(retorno)).getDays();
        return String.valueOf(days);
    }

    public void procesarFactura(Object document) {
        Document pdf = (Document) document;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdf.open();
        try {
            PdfWriter.getInstance(pdf, out);
            if (pdf.isOpen()) {
                pdf.close();
            }

        } catch (DocumentException ex) {
            Logger.getLogger(FacturaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
        //RequestContext.getCurrentInstance().execute("PF('dialog_var').show()");
    }

    public void send() {
        this.emailSessionBean.sendEmail(this.sessionBean.getUser().getCorreo(), "prueba", "hola","ajaja");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Éxito", "El email ha sido enviado"));
    }

}
