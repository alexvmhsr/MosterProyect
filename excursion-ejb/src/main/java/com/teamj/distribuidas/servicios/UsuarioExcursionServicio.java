/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.UsuarioExcursionDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.UsuarioExcursion;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class UsuarioExcursionServicio {

    @EJB
    private UsuarioExcursionDAO usuarioExcursionDAO;

    public boolean registrarUsuarioEnExcursion(UsuarioExcursion usuarioExcursion) {

        try {
            this.usuarioExcursionDAO.insert(usuarioExcursion);
        } catch (Exception e) {
            throw new ValidationException(e, "Error al asignar el usuario en la excursion");
        }
        return false;

    }
}
