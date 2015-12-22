/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Usuario;
import java.io.Serializable;
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
public class MenuBean implements Serializable {

    private String perfil;
    private String excursiones;
    private String mochila;
    private String comentarios;
    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void login(Usuario u) {
        this.user = u;
    }

    public void logout() {
        this.user = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
    }

}
