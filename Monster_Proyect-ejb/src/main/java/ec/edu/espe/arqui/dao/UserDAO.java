/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.persist.common.dao.DefaultGenericDAOImple;
import ec.edu.espe.arqui.modelo.MonsUser;

/**
 *
 * @author PabloA
 */

@LocalBean
@Stateless
public class UserDAO extends DefaultGenericDAOImple<MonsUser, Integer>{
    public UserDAO() {
        super(MonsUser.class);
    }
}
