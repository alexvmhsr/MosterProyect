/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ComentarioDAO;
import com.teamj.distribuidas.model.Comentario;
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
public class ComentarioServicio {
    @EJB
    private ComentarioDAO comentarioDAO;
    public List<Comentario> obtenerTodas() {

        return comentarioDAO.findAll();
    }

    public void insertar(Comentario m) {
        try {
            this.comentarioDAO.insert(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
     public void actualizar(Comentario m) {
        try {
            this.comentarioDAO.update(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(Integer id) {
        Comentario temp = this.comentarioDAO.findById(id, false);
        if (temp != null) {
            this.comentarioDAO.remove(temp);
        }
    }
}
