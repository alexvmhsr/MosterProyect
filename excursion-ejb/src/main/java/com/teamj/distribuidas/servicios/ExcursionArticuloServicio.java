/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ExcursionArticuloDAO;
import com.teamj.distribuidas.dao.ExcursionDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.model.ExcursionArticuloPK;
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
public class ExcursionArticuloServicio {

    @EJB
    private ExcursionArticuloDAO excursionArticuloDAO;

    public List<ExcursionArticulo> obtenerTodas() {

        return excursionArticuloDAO.findAll();
    }

    public List<ExcursionArticulo> obtenerPorExcursionId(Excursion e) {
        ExcursionArticulo articulo = new ExcursionArticulo();
        articulo.setExcursion(e);
        return excursionArticuloDAO.find(articulo);
    }

    public void insertar(ExcursionArticulo m) throws ValidationException {
        try {
            this.excursionArticuloDAO.insert(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            throw new ValidationException(e, "Ha ocurrido un error");
        }
    }

    public void actualizar(ExcursionArticulo m) {
        try {
            this.excursionArticuloDAO.update(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void eliminar(ExcursionArticuloPK id) {
        ExcursionArticulo temp = this.excursionArticuloDAO.findById(id, false);
        if (temp != null) {
            this.excursionArticuloDAO.remove(temp);
        }
    }

}
