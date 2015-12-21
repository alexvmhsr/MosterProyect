/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ActividadDAO;
import com.teamj.distribuidas.model.Actividad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class ActividadServicio {
    @EJB
    private ActividadDAO actividadDAO;
    
    public List<Actividad> obtenerTodas() {

        return actividadDAO.findAll();
    }

    public void insertar(Actividad m) {
        try {
            this.actividadDAO.insert(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(Integer id) {
        Actividad temp = this.actividadDAO.findById(id, false);
        if (temp != null) {
            this.actividadDAO.remove(temp);
        }
    }
}
