/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author Gaming
 */
@ViewScoped
@ManagedBean
public class ReporteBean implements Serializable {
    
    
    private java.util.Date fechaAntes;
    private java.util.Date fechaDespues;
    private int idFactura;
    private boolean reporte3=false;
    private StreamedContent content;
    private boolean reporteFactura=false;
    public boolean isReporte3() {
        return reporte3;
    }

    public void setReporte3(boolean reporte3) {
        this.reporte3 = reporte3;
    }
    public boolean isReporteFactura() {
        return reporteFactura;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setReportefactura(boolean reporteFactura) {
        this.reporteFactura = reporteFactura;
    }
    public void generarReporteStock()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "excursion", "excursion");
            System.out.println("Conexion realizada");
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("Dentro del try");
            ServletContext servercontext = (ServletContext) context.getExternalContext().getContext();
            System.out.println("Dentro del servlet");
            String path = servercontext.getRealPath("/reportes/reportStock.jasper");
            System.out.println(path + "se hizo el cambio");
            System.out.println("Ruta del archivo");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            System.out.println("Response");
            response.addHeader("Content-disposition", "attachment;filename=reporte.pdf");
            System.out.println("Archivo");
            response.setContentType("application/pdf");
            System.out.println("Formato PDf");
            JasperPrint jp = JasperFillManager.fillReport(path, null, conn);            
            System.out.println("Ruta");
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            System.out.println("exporrtando");
            context.getApplication().getStateManager().saveView(context);
            System.out.println("Guardando");
            context.responseComplete();
        }
        catch(Exception a)
        {
            System.out.println(a.toString());
        }
    }
    
    public void generarReporteMonto()
    {
        try
        {
            
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "excursion", "excursion");
            System.out.println("Conexion realizada");
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("Dentro del try");
            ServletContext servercontext = (ServletContext) context.getExternalContext().getContext();
            System.out.println("Dentro del servlet");
            String path = servercontext.getRealPath("/reportes/reportMonto.jasper");
            System.out.println(path + "se hizo el cambio");
            System.out.println("Ruta del archivo");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            System.out.println("Response");
            response.addHeader("Content-disposition", "attachment;filename=reporte.pdf");
            System.out.println("Archivo");
            response.setContentType("application/pdf");
            System.out.println("Formato PDf");
            JasperPrint jp = JasperFillManager.fillReport(path, null, conn);            
            System.out.println("Ruta");
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            System.out.println("exporrtando");
            context.getApplication().getStateManager().saveView(context);
            System.out.println("Guardando");
            context.responseComplete();
        }
        catch(Exception a)
        {
            System.out.println(a.toString());
        }
    }
    
    public void generarReporteTop5()
    {
        try
        {
            
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "excursion", "excursion");
            System.out.println("Conexion realizada");
            System.out.println("fecha antes: "+fechaAntes.toString());
            System.out.println("fecha Despues: "+fechaDespues.toString());
            Map parametros = new HashMap();
            parametros.put("fechaAntes", fechaAntes);
            parametros.put("fechaDespues", fechaDespues);
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("Dentro del try");
            ServletContext servercontext = (ServletContext) context.getExternalContext().getContext();
            System.out.println("Dentro del servlet");
            String path = servercontext.getRealPath("/reportes/reportTop5.jasper");
            System.out.println(path + "se hizo el cambio");
            System.out.println("Ruta del archivo");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            System.out.println("Response");
            response.addHeader("Content-disposition", "inline;filename=reporte.pdf");
            System.out.println("Archivo");
            response.setContentType("application/pdf");
            System.out.println("Formato PDf");
            JasperPrint jp = JasperFillManager.fillReport(path, parametros, conn);            
            System.out.println("Ruta");
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            System.out.println("exporrtando");
            context.getApplication().getStateManager().saveView(context);
            System.out.println("Guardando");
            context.responseComplete();
        }
        catch(Exception a)
        {
            System.out.println(a.toString());
        }
    }
    
    public void generarFactura()
    {
        try
        {
            
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "excursion", "excursion");
            System.out.println("Conexion realizada");            
            Map parametros = new HashMap();
            parametros.put("idFactura", 41);
            parametros.put("SUBREPORT_DIR", "C:\\Users\\Gaming\\Documents\\NetBeansProjects\\ProyectoSegundoParcial\\excursion-web\\src\\main\\webapp\\reportes\\");
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("Dentro del try");
            ServletContext servercontext = (ServletContext) context.getExternalContext().getContext();
            System.out.println("Dentro del servlet");
            String path = servercontext.getRealPath("/reportes/ReportFactura.jasper");
            System.out.println(path + "se hizo el cambio");
            System.out.println("Ruta del archivo");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            System.out.println("Response");
            response.addHeader("Content-disposition", "inline;filename=\"reporte.pdf\"");
            System.out.println("Archivo");
            response.setContentType("application/pdf");
            System.out.println("Formato PDf");
            JasperPrint jp = JasperFillManager.fillReport(path, parametros, conn);            
            System.out.println("Ruta");
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            
            System.out.println("exporrtando");
            context.getApplication().getStateManager().saveView(context);
            System.out.println("Guardando");
            
            //para mostrar en un pdf
            //ByteArrayOutputStream out = new ByteArrayOutputStream();
            //JasperExportManager.exportReportToPdfStream(jp, out);
           //content=new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()));

            context.responseComplete();
        }
        catch(Exception a)
        {
            System.out.println(a.toString());
        }
    }
    public void mostrarParametrosReporte3(){
    this.reporte3=true;
    }
    public void mostrarParametrosFactura(){
    this.reporteFactura=true;
    }
    public java.util.Date getFechaAntes() {
        return fechaAntes;
    }

    public void setFechaAntes(java.util.Date fechaAntes) {
        this.fechaAntes = fechaAntes;
    }

    public java.util.Date getFechaDespues() {
        return fechaDespues;
    }

    public void setFechaDespues(java.util.Date fechaDespues) {
        this.fechaDespues = fechaDespues;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
}
