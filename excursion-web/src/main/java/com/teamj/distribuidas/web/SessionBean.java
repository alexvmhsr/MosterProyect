/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dennys
 */
@SessionScoped
@ManagedBean
public class SessionBean implements Serializable {

    public static final String HOME_PAGE_REDIRECT = "/user/home.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";

    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public String login(Usuario u) {

        this.user = u;

        return HOME_PAGE_REDIRECT;

    }

    public String logout() {
        this.user = null;
        // invalidate the session
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
        return null;
    }
}
