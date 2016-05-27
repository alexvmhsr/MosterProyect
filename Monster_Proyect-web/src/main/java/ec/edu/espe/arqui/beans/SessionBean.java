/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.beans;

import ec.edu.espe.arqui.modelo.MonsUser;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author PabloA
 */
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }

    
    public static final String HOME_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";
    
    private MonsUser user;

    public MonsUser getUser() {
        return user;
    }

    public String login(MonsUser u) {

        this.user = u;

        return HOME_PAGE_REDIRECT;

    }

    public String logout() {
        // invalidate the session
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return user != null;
    }


/**
 * 
 * @return si es el usuario administrador del sistema
 */
    public boolean isAdmin() {
        if (user != null) {
            return user.getUserGrupo().equals("Admin");
        }
        return false;
    }
    
}
