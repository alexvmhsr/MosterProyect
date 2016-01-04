/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ExcursionDAO;
import com.teamj.distribuidas.dao.MochilaDAO;
import com.teamj.distribuidas.dao.UsuarioExcursionDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.Mochila;
import com.teamj.distribuidas.model.Usuario;
import com.teamj.distribuidas.model.UsuarioExcursion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Gaming
 */
@LocalBean
@Stateless
public class ExcursionServicio {

    @EJB
    private ExcursionDAO excursionDAO;

    @EJB
    private MochilaDAO mochilaDAO;

    @EJB
    private UsuarioExcursionDAO usuarioExcursionDAO;

    public List<Excursion> obtenerTodas() {

        return excursionDAO.findAll();
    }

    public void insertar(Excursion e, Usuario u) throws ValidationException {
        Mochila m = new Mochila();
        m.setNombre("Creador");
        try {
            List<Mochila> tempMochilas = this.mochilaDAO.find(m);
            if (tempMochilas != null && tempMochilas.size() == 1) {
                this.excursionDAO.insert(e);
                UsuarioExcursion usuarioExcursion = new UsuarioExcursion();
                usuarioExcursion.setMochila(tempMochilas.get(0));
                usuarioExcursion.setUsuario(u);
                usuarioExcursion.setExcursion(e);
                this.usuarioExcursionDAO.insert(usuarioExcursion);

            }
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear Excursión");
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

    public void participar(Excursion e, Usuario u, Mochila m) throws ValidationException {
        try {
            this.excursionDAO.insert(e);
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear Excursión");
        }
    }
}
