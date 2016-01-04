/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.UsuarioDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class UsuarioServicio {

    @EJB
    private UsuarioDAO usuarioDAO;

    public boolean insertar(Usuario u) throws ValidationException {
        boolean flag = false;
        Usuario temp = new Usuario();
        temp.setNombre(u.getNombre());
        List<Usuario> tempList = this.usuarioDAO.find(temp);
        if (tempList == null || tempList.isEmpty()) {//el nombre de usuario no existe
            try {
                String codecPassword = DigestUtils.md5Hex(u.getPassword());
                u.setPassword(codecPassword);
                this.usuarioDAO.insert(u);
                flag = true;

            } catch (Exception e) {
                throw new ValidationException(e, "Error al crear el nuevo usuario");
            }
        }
        return flag;
    }

    public void eliminar(Integer id) {
        Usuario temp = this.usuarioDAO.findById(id, false);
        if (temp != null) {
            this.usuarioDAO.remove(temp);
        }
    }

    public Usuario login(Usuario userOnlyWithUsername, String password) {
        List<Usuario> tempList = this.usuarioDAO.find(userOnlyWithUsername);
        if (tempList != null && tempList.size() == 1) {
            if (DigestUtils.md5Hex(password).equals(tempList.get(0).getPassword())) {
                return tempList.get(0);
            }
        }
        return null;
    }

    public boolean actualizar(Usuario u) throws ValidationException {
        boolean flag = false;

        try {

            this.usuarioDAO.update(u);
            flag = true;

        } catch (Exception e) {
            throw new ValidationException(e, "Error  al actualizar el usuario");
        }

        return flag;
    }

    public boolean cambiarContraseña(Usuario u, String oldP, String newP, String reNewP) throws ValidationException {
        boolean flag = false;

        try {
            if (DigestUtils.md5Hex(oldP).equals(u.getPassword()) && newP.equals(reNewP)) {
                u.setPassword(DigestUtils.md5Hex(newP));
                this.usuarioDAO.update(u);
                flag = true;
            }
        } catch (Exception e) {
            throw new ValidationException(e, "Error  al actualizar el usuario");
        }

        return flag;
    }

}
