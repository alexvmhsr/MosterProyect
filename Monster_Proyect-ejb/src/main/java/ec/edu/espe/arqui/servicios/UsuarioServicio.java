/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.servicios;

import ec.edu.espe.arqui.dao.UserDAO;
import ec.edu.espe.arqui.exception.ValidationException;
import ec.edu.espe.arqui.modelo.MonsUser;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author PabloA
 */
@LocalBean
@Stateless
public class UsuarioServicio {
    @EJB
    private UserDAO usuarioDAO;
    

    public boolean insertar(MonsUser u) throws ValidationException {
        boolean flag = false;
        MonsUser temp = new MonsUser();
        temp.setUserNombre(u.getUserNombre());
        List<MonsUser> tempList = this.usuarioDAO.find(temp);
        if (tempList == null || tempList.isEmpty()) {//el nombre de usuario no existe
            try {
                String codecPassword = DigestUtils.md5Hex(u.getUserPasword());
                u.setUserPasword(codecPassword);
                this.usuarioDAO.insert(u);

            } catch (Exception e) {
                throw new ValidationException(e, "Error al crear el nuevo usuario");
            }
        }
        return flag;
    }

    public void eliminar(Integer id) {
        MonsUser temp = this.usuarioDAO.findById(id, false);
        if (temp != null) {
            this.usuarioDAO.remove(temp);
        }
    }

    public MonsUser login(MonsUser userOnlyWithUsername, String password) {
        List<MonsUser> tempList = this.usuarioDAO.find(userOnlyWithUsername);
        if (tempList != null && tempList.size() == 1) {
            if (DigestUtils.md5Hex(password).equals(tempList.get(0).getUserPasword())) {
                return tempList.get(0);
            }
        }
        return null;
    }

    

    public boolean cambiarContraseña(MonsUser u, String oldP, String newP, String reNewP) throws ValidationException {
        boolean flag = false;
        try {
            if (DigestUtils.md5Hex(oldP).equals(u.getUserPasword()) && newP.equals(reNewP)) {
                u.setUserPasword(DigestUtils.md5Hex(newP));
                this.usuarioDAO.update(u);
                flag = true;
            }
        } catch (Exception e) {
            throw new ValidationException(e, "Error  al actualizar el usuario");
        }
        return flag;
    }
}
