/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ArticuloDAO;
import com.teamj.distribuidas.model.Articulo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Gaming
 */
@LocalBean
@Stateless
public class ArticuloServicio {
    
    @EJB
    private ArticuloDAO excursionDAO;

    public List<Articulo> obtenerTodas() {

        return excursionDAO.findAll();
    }
//    public List<Articulo> obtenerTodasActivas() {
//
//        //query.setParameter("date", new java.util.Date(), TemporalType.DATE);
//        //return excursionDAO.getEm().createQuery("SELECT e FROM Excursion e WHERE e.fechaLimite > 'Canada'");
//    }

    public void insertar(Articulo m) {
        try {
            this.excursionDAO.insert(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
     public void actualizar(Articulo m) {
        try {
            this.excursionDAO.update(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(Integer id) {
        Articulo temp = this.excursionDAO.findById(id, false);
        if (temp != null) {
            this.excursionDAO.remove(temp);
        }
    }
    
}
