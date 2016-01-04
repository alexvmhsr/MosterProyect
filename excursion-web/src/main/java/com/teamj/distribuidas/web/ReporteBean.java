/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import java.sql.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
/**
 *
 * @author Gaming
 */
@ViewScoped
@ManagedBean
public class ReporteBean implements Serializable {
    
    
    
    
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
            
}
