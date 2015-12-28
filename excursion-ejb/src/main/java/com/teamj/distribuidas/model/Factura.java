/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "FACTURA")
public class Factura implements Serializable{

    @Id
    @SequenceGenerator(name = "FACTURA_SEQ1", sequenceName = "FACTURA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "FACTURA_SEQ1")
    @Column(name = "ID_FACTURA")
    private Integer id;

    @Column(name = "TIPO_DOC_EMISOR")
    private String tipoDocEmisor;

    @Column(name = "TIPO_DOC_RECEPTOR")
    private String tipoDocReceptor;

    @Column(name = "DOC_EMISOR")
    private String docEmisor;

    @Column(name = "DOC_RECEPTOR")
    private String docReceptor;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "DIRECCION")
    private String direccionEmisor;
    @Column(name = "SECUENCIAL")
    private String secuencial;

    @Column(name = "FECHA_EMISION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @Column(name = "FECHA_AUTORIZACION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;

    @Column(name = "CLAVEACCESO")
    private String claveAcceso;

    @Column(name = "NUMERO_AUTORIZACION")
    private String numeroAutorizacion;

    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "DESCUENTO")
    private BigDecimal decuento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoDocEmisor() {
        return tipoDocEmisor;
    }

    public void setTipoDocEmisor(String tipoDocEmisor) {
        this.tipoDocEmisor = tipoDocEmisor;
    }

    public String getTipoDocReceptor() {
        return tipoDocReceptor;
    }

    public void setTipoDocReceptor(String tipoDocReceptor) {
        this.tipoDocReceptor = tipoDocReceptor;
    }

    public String getDocEmisor() {
        return docEmisor;
    }

    public void setDocEmisor(String docEmisor) {
        this.docEmisor = docEmisor;
    }

    public String getDocReceptor() {
        return docReceptor;
    }

    public void setDocReceptor(String docReceptor) {
        this.docReceptor = docReceptor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccionEmisor() {
        return direccionEmisor;
    }

    public void setDireccionEmisor(String direccionEmisor) {
        this.direccionEmisor = direccionEmisor;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDecuento() {
        return decuento;
    }

    public void setDecuento(BigDecimal decuento) {
        this.decuento = decuento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", tipoDocEmisor=" + tipoDocEmisor + ", tipoDocReceptor=" + tipoDocReceptor + ", docEmisor=" + docEmisor + ", docReceptor=" + docReceptor + ", razonSocial=" + razonSocial + ", direccionEmisor=" + direccionEmisor + ", secuencial=" + secuencial + ", fechaEmision=" + fechaEmision + ", fechaAutorizacion=" + fechaAutorizacion + ", claveAcceso=" + claveAcceso + ", numeroAutorizacion=" + numeroAutorizacion + ", subtotal=" + subtotal + ", total=" + total + ", decuento=" + decuento + '}';
    }

}
