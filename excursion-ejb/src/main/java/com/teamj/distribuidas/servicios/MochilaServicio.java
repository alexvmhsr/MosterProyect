/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.MochilaDAO;
import com.teamj.distribuidas.model.Mochila;
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
public class MochilaServicio {

    @EJB
    private MochilaDAO mochilaDAO;

    public List<Mochila> obtenerTodas() {

        return mochilaDAO.findAll();
    }

    public void insertar(Mochila m) {
        try {
            this.mochilaDAO.insert(m);
            this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(Integer id) {
        Mochila temp = this.mochilaDAO.findById(id, false);
        if (temp != null) {
            this.mochilaDAO.remove(temp);
        }
    }

}
