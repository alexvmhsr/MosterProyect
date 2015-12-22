/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ExcursionDAO;
import com.teamj.distribuidas.model.Excursion;
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
public class ExcursionServicio {
    
    @EJB
    private ExcursionDAO excursionDAO;

    public List<Excursion> obtenerTodas() {

        return excursionDAO.findAll();
    }

    public void insertar(Excursion m) {
        try {
            this.excursionDAO.insert(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
     public void actualizar(Excursion m) {
        try {
            this.excursionDAO.update(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(Integer id) {
        Excursion temp = this.excursionDAO.findById(id, false);
        if (temp != null) {
            this.excursionDAO.remove(temp);
        }
    }
    
}
